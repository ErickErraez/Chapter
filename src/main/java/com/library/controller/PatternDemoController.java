package com.library.controller;

import com.library.model.Book;
import com.library.patterns.chainofresponsibility.BookValidationHandler;
import com.library.patterns.decorator.DigitalBookDecorator;
import com.library.patterns.decorator.PrestamoDecorator;
import com.library.patterns.decorator.ReservedBookDecorator;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.*;

@RestController
@RequestMapping("/api/patterns")
@CrossOrigin(origins = "*")
public class PatternDemoController {
    
    private final BookValidationHandler validationHandler = new BookValidationHandler();
    
    @PostMapping("/validation/book")
    public ResponseEntity<ValidationResult> validateBook(@RequestBody Book book) {
        try {
            validationHandler.handleRequest(book);
            return ResponseEntity.ok(new ValidationResult(true, "Libro válido"));
        } catch (Exception e) {
            return ResponseEntity.ok(new ValidationResult(false, "Validación fallida: " + e.getMessage()));
        }
    }
    
    @PostMapping("/decorator/loan")
    public ResponseEntity<DecoratorResult> decorateWithLoan(@RequestBody Book book) {
        try {
            PrestamoDecorator decorator = new PrestamoDecorator(book);
            decorator.loan();
            
            return ResponseEntity.ok(new DecoratorResult(
                "PrestamoDecorator",
                decorator.getDescription(),
                decorator.isLoaned(),
                "Funcionalidad de préstamo agregada al libro"
            ));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new DecoratorResult(
                "PrestamoDecorator",
                "Error",
                false,
                "Error al aplicar decorator: " + e.getMessage()
            ));
        }
    }
    
    @PostMapping("/decorator/digital")
    public ResponseEntity<DecoratorResult> decorateWithDigital(@RequestBody DigitalDecoratorRequest request) {
        try {
            DigitalBookDecorator decorator = new DigitalBookDecorator(request.getBook(), request.getFileFormat());
            
            return ResponseEntity.ok(new DecoratorResult(
                "DigitalBookDecorator",
                decorator.getDescription(),
                true,
                "Funcionalidad digital agregada con formato: " + request.getFileFormat()
            ));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new DecoratorResult(
                "DigitalBookDecorator",
                "Error",
                false,
                "Error al aplicar decorator: " + e.getMessage()
            ));
        }
    }
    
    @PostMapping("/decorator/reserved")
    public ResponseEntity<DecoratorResult> decorateWithReserved(@RequestBody Book book) {
        try {
            ReservedBookDecorator decorator = new ReservedBookDecorator(book);
            
            return ResponseEntity.ok(new DecoratorResult(
                "ReservedBookDecorator",
                decorator.getDescription(),
                decorator.isReserved(),
                "Funcionalidad de reserva agregada al libro"
            ));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new DecoratorResult(
                "ReservedBookDecorator",
                "Error",
                false,
                "Error al aplicar decorator: " + e.getMessage()
            ));
        }
    }
    
    @GetMapping("/patterns-info")
    public ResponseEntity<Map<String, String>> getPatternsInfo() {
        Map<String, String> patterns = new HashMap<>();
        patterns.put("Singleton", "DatabaseConnection - Una sola instancia de conexión a BD");
        patterns.put("Factory Method", "FiccionFactory, NoFiccionFactory - Creación de libros por tipo");
        patterns.put("Abstract Factory", "LibroFisicoFactory, LibroDigitalFactory - Familias de libros");
        patterns.put("Builder", "BookBuilder - Construcción flexible de libros");
        patterns.put("Strategy", "SearchByTitle, SearchByAuthor - Diferentes estrategias de búsqueda");
        patterns.put("Observer", "NotificationService - Notificaciones de cambios");
        patterns.put("Decorator", "PrestamoDecorator, DigitalBookDecorator - Funcionalidades adicionales");
        patterns.put("Chain of Responsibility", "BookValidationHandler - Validaciones encadenadas");
        patterns.put("Adapter", "LegacyLibroAdapter - Integración de sistemas antiguos");
        
        return ResponseEntity.ok(patterns);
    }
    
    @GetMapping("/solid-principles")
    public ResponseEntity<Map<String, String>> getSOLIDInfo() {
        Map<String, String> principles = new HashMap<>();
        principles.put("S - Single Responsibility", "Cada clase tiene una responsabilidad específica");
        principles.put("O - Open/Closed", "Abierto para extensión, cerrado para modificación");
        principles.put("L - Liskov Substitution", "Las subclases deben ser sustituibles por sus clases base");
        principles.put("I - Interface Segregation", "Interfaces específicas mejor que una general");
        principles.put("D - Dependency Inversion", "Depender de abstracciones, no de concreciones");
        
        return ResponseEntity.ok(principles);
    }
    
    // DTOs para requests y responses
    public static class ValidationResult {
        private boolean valid;
        private String message;
        
        public ValidationResult(boolean valid, String message) {
            this.valid = valid;
            this.message = message;
        }
        
        public boolean isValid() { return valid; }
        public void setValid(boolean valid) { this.valid = valid; }
        public String getMessage() { return message; }
        public void setMessage(String message) { this.message = message; }
    }
    
    public static class DecoratorResult {
        private String decoratorType;
        private String description;
        private boolean applied;
        private String message;
        
        public DecoratorResult(String decoratorType, String description, boolean applied, String message) {
            this.decoratorType = decoratorType;
            this.description = description;
            this.applied = applied;
            this.message = message;
        }
        
        public String getDecoratorType() { return decoratorType; }
        public void setDecoratorType(String decoratorType) { this.decoratorType = decoratorType; }
        public String getDescription() { return description; }
        public void setDescription(String description) { this.description = description; }
        public boolean isApplied() { return applied; }
        public void setApplied(boolean applied) { this.applied = applied; }
        public String getMessage() { return message; }
        public void setMessage(String message) { this.message = message; }
    }
    
    public static class DigitalDecoratorRequest {
        private Book book;
        private String fileFormat;
        
        public Book getBook() { return book; }
        public void setBook(Book book) { this.book = book; }
        public String getFileFormat() { return fileFormat; }
        public void setFileFormat(String fileFormat) { this.fileFormat = fileFormat; }
    }
}