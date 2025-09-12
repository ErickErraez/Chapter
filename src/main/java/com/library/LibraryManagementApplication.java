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
import java.util.*;

public class LibraryManagementApplication {
    public static void main(String[] args) {
        // Observer setup
        NotificationService notificationService = new NotificationService();
        notificationService.registerObserver(new LibraryObserver("Admin"));

        // Chain of Responsibility setup
        BookValidationHandler validationHandler = new BookValidationHandler();

        // Abstract Factory usage
        LibroFisicoFactory fisicoFactory = new LibroFisicoFactory();
        LibroDigitalFactory digitalFactory = new LibroDigitalFactory();

        // Factory Method usage
        Book ficcionBook = FiccionFactory.createFiccionBook("El Quijote", "Cervantes", "Físico");
        Book noFiccionBook = NoFiccionFactory.createNoFiccionBook("Breve historia del tiempo", "Hawking", "Digital");

        // Builder usage
        Book customBook = new BookBuilder()
                .setTitle("Clean Code")
                .setAuthor("Robert C. Martin")
                .setType("No Ficción")
                .setFormat("Físico")
                .setStatus("Disponible")
                .build();

        // Adapter usage
        LegacyLibro legacy = new LegacyLibro(100L, "Libro Antiguo", "Autor Antiguo", "Ficción", "Físico", "Disponible");
        ILibro legacyAdapted = new LegacyLibroAdapter(legacy);

        // Validaciones
        validationHandler.handleRequest(ficcionBook);
        validationHandler.handleRequest(noFiccionBook);
        validationHandler.handleRequest(customBook);

        // Listado de libros
        List<Book> books = new ArrayList<>();
        books.add(ficcionBook);
        books.add(noFiccionBook);
        books.add(customBook);

        // Decorator: préstamo
        PrestamoDecorator prestamo = new PrestamoDecorator(ficcionBook);
        prestamo.loan();
        notificationService.notifyObservers("El libro '" + ficcionBook.getTitle() + "' ha sido prestado.");

        // Strategy: búsqueda
        SearchContext searchByTitle = new SearchContext(new SearchByTitle("El Quijote"));
        List<Book> foundByTitle = searchByTitle.executeSearch(books);
        System.out.println("Búsqueda por título: " + foundByTitle.size() + " resultado(s).");

        SearchContext searchByAuthor = new SearchContext(new SearchByAuthor("Hawking"));
        List<Book> foundByAuthor = searchByAuthor.executeSearch(books);
        System.out.println("Búsqueda por autor: " + foundByAuthor.size() + " resultado(s).");

        // Mostrar todos los libros
        System.out.println("Listado de libros:");
        for (Book b : books) {
            System.out.println(b.getTitle() + " - " + b.getAuthor() + " - " + b.getType() + " - " + b.getFormat() + " - " + b.getStatus());
        }

        // Mostrar libro adaptado
        System.out.println("Libro adaptado: " + legacyAdapted.getTitle() + " - " + legacyAdapted.getAuthor());
    }
}