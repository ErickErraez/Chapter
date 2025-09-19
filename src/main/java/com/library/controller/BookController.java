package com.library.controller;

import com.library.model.Book;
import com.library.patterns.factory.FiccionFactory;
import com.library.patterns.factory.NoFiccionFactory;
import com.library.patterns.abstractfactory.LibroFisicoFactory;
import com.library.patterns.abstractfactory.LibroDigitalFactory;
import com.library.patterns.builder.BookBuilder;
import com.library.patterns.strategy.SearchByTitle;
import com.library.patterns.strategy.SearchByAuthor;
import com.library.patterns.strategy.SearchContext;
import com.library.patterns.observer.LibraryObserver;
import com.library.patterns.observer.NotificationService;
import com.library.patterns.decorator.PrestamoDecorator;
import com.library.patterns.adapter.LegacyLibro;
import com.library.patterns.adapter.LegacyLibroAdapter;
import com.library.patterns.adapter.ILibro;
import com.library.patterns.chainofresponsibility.BookValidationHandler;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/api/books")
@CrossOrigin(origins = "*")
public class BookController {
    
    private final Map<Long, Book> books = new ConcurrentHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(1);
    private final NotificationService notificationService = new NotificationService();
    private final BookValidationHandler validationHandler = new BookValidationHandler();
    
    public BookController() {
        // Configurar observador
        notificationService.registerObserver(new LibraryObserver("Admin"));
        
        // Agregar algunos libros de ejemplo
        initializeExampleBooks();
    }
    
    private void initializeExampleBooks() {
        Book book1 = FiccionFactory.createFiccionBook("El Quijote", "Cervantes", "Físico");
        book1.setId(idGenerator.getAndIncrement());
        books.put(book1.getId(), book1);
        
        Book book2 = NoFiccionFactory.createNoFiccionBook("Breve historia del tiempo", "Hawking", "Digital");
        book2.setId(idGenerator.getAndIncrement());
        books.put(book2.getId(), book2);
        
        Book book3 = new BookBuilder()
                .setTitle("Clean Code")
                .setAuthor("Robert C. Martin")
                .setType("No Ficción")
                .setFormat("Físico")
                .setStatus("Disponible")
                .build();
        book3.setId(idGenerator.getAndIncrement());
        books.put(book3.getId(), book3);
    }
    
    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks() {
        return ResponseEntity.ok(new ArrayList<>(books.values()));
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        Book book = books.get(id);
        return book != null ? ResponseEntity.ok(book) : ResponseEntity.notFound().build();
    }
    
    @PostMapping
    public ResponseEntity<Book> createBook(@RequestBody BookRequest request) {
        try {
            Book book;
            
            // Usar Factory Method según el tipo
            if ("Ficción".equals(request.getType())) {
                book = FiccionFactory.createFiccionBook(request.getTitle(), request.getAuthor(), request.getFormat());
            } else {
                book = NoFiccionFactory.createNoFiccionBook(request.getTitle(), request.getAuthor(), request.getFormat());
            }
            
            book.setId(idGenerator.getAndIncrement());
            
            // Validar usando Chain of Responsibility
            validationHandler.handleRequest(book);
            
            books.put(book.getId(), book);
            
            return ResponseEntity.ok(book);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    @PostMapping("/builder")
    public ResponseEntity<Book> createBookWithBuilder(@RequestBody BookBuilderRequest request) {
        try {
            Book book = new BookBuilder()
                    .setTitle(request.getTitle())
                    .setAuthor(request.getAuthor())
                    .setType(request.getType())
                    .setFormat(request.getFormat())
                    .setStatus(request.getStatus() != null ? request.getStatus() : "Disponible")
                    .build();
            
            book.setId(idGenerator.getAndIncrement());
            
            // Validar usando Chain of Responsibility
            validationHandler.handleRequest(book);
            
            books.put(book.getId(), book);
            
            return ResponseEntity.ok(book);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    @PostMapping("/physical")
    public ResponseEntity<Book> createPhysicalBook(@RequestBody BookRequest request) {
        LibroFisicoFactory factory = new LibroFisicoFactory();
        Book book = factory.create(request.getTitle(), request.getAuthor(), request.getType());
        book.setId(idGenerator.getAndIncrement());
        
        validationHandler.handleRequest(book);
        books.put(book.getId(), book);
        
        return ResponseEntity.ok(book);
    }
    
    @PostMapping("/digital")
    public ResponseEntity<Book> createDigitalBook(@RequestBody BookRequest request) {
        LibroDigitalFactory factory = new LibroDigitalFactory();
        Book book = factory.create(request.getTitle(), request.getAuthor(), request.getType());
        book.setId(idGenerator.getAndIncrement());
        
        validationHandler.handleRequest(book);
        books.put(book.getId(), book);
        
        return ResponseEntity.ok(book);
    }
    
    @GetMapping("/search/title/{title}")
    public ResponseEntity<List<Book>> searchByTitle(@PathVariable String title) {
        SearchContext context = new SearchContext(new SearchByTitle(title));
        List<Book> results = context.executeSearch(new ArrayList<>(books.values()));
        return ResponseEntity.ok(results);
    }
    
    @GetMapping("/search/author/{author}")
    public ResponseEntity<List<Book>> searchByAuthor(@PathVariable String author) {
        SearchContext context = new SearchContext(new SearchByAuthor(author));
        List<Book> results = context.executeSearch(new ArrayList<>(books.values()));
        return ResponseEntity.ok(results);
    }
    
    @PostMapping("/{id}/loan")
    public ResponseEntity<String> loanBook(@PathVariable Long id) {
        Book book = books.get(id);
        if (book == null) {
            return ResponseEntity.notFound().build();
        }
        
        if (!"Disponible".equals(book.getStatus())) {
            return ResponseEntity.badRequest().body("El libro no está disponible para préstamo");
        }
        
        // Usar Decorator para el préstamo
        PrestamoDecorator prestamo = new PrestamoDecorator(book);
        prestamo.loan();
        
        // Notificar usando Observer
        notificationService.notifyObservers("El libro '" + book.getTitle() + "' ha sido prestado.");
        
        return ResponseEntity.ok("Libro prestado exitosamente");
    }
    
    @PostMapping("/{id}/return")
    public ResponseEntity<String> returnBook(@PathVariable Long id) {
        Book book = books.get(id);
        if (book == null) {
            return ResponseEntity.notFound().build();
        }
        
        if (!"Prestado".equals(book.getStatus())) {
            return ResponseEntity.badRequest().body("El libro no está prestado");
        }
        
        // Usar Decorator para la devolución
        PrestamoDecorator prestamo = new PrestamoDecorator(book);
        prestamo.returnBook();
        
        // Notificar usando Observer
        notificationService.notifyObservers("El libro '" + book.getTitle() + "' ha sido devuelto.");
        
        return ResponseEntity.ok("Libro devuelto exitosamente");
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        Book removedBook = books.remove(id);
        return removedBook != null ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }
    
    // DTOs para requests
    public static class BookRequest {
        private String title;
        private String author;
        private String type;
        private String format;
        
        // Getters y setters
        public String getTitle() { return title; }
        public void setTitle(String title) { this.title = title; }
        public String getAuthor() { return author; }
        public void setAuthor(String author) { this.author = author; }
        public String getType() { return type; }
        public void setType(String type) { this.type = type; }
        public String getFormat() { return format; }
        public void setFormat(String format) { this.format = format; }
    }
    
    public static class BookBuilderRequest extends BookRequest {
        private String status;
        
        public String getStatus() { return status; }
        public void setStatus(String status) { this.status = status; }
    }
}