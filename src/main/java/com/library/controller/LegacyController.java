package com.library.controller;

import com.library.patterns.adapter.LegacyLibro;
import com.library.patterns.adapter.LegacyLibroAdapter;
import com.library.patterns.adapter.ILibro;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/api/legacy")
@CrossOrigin(origins = "*")
public class LegacyController {
    
    private final Map<Long, LegacyLibro> legacyBooks = new ConcurrentHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(100);
    
    public LegacyController() {
        // Agregar algunos libros legacy de ejemplo
        initializeExampleLegacyBooks();
    }
    
    private void initializeExampleLegacyBooks() {
        LegacyLibro legacy1 = new LegacyLibro(idGenerator.getAndIncrement(), 
                "Libro Antiguo 1", "Autor Antiguo 1", "Ficción", "Físico", "Disponible");
        legacyBooks.put(legacy1.obtenerId(), legacy1);
        
        LegacyLibro legacy2 = new LegacyLibro(idGenerator.getAndIncrement(), 
                "Libro Antiguo 2", "Autor Antiguo 2", "No Ficción", "Físico", "Prestado");
        legacyBooks.put(legacy2.obtenerId(), legacy2);
    }
    
    @GetMapping
    public ResponseEntity<List<ILibro>> getAllLegacyBooks() {
        List<ILibro> adaptedBooks = legacyBooks.values().stream()
                .map(LegacyLibroAdapter::new)
                .map(adapter -> (ILibro) adapter)
                .toList();
        return ResponseEntity.ok(adaptedBooks);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<ILibro> getLegacyBookById(@PathVariable Long id) {
        LegacyLibro legacyBook = legacyBooks.get(id);
        if (legacyBook != null) {
            ILibro adaptedBook = new LegacyLibroAdapter(legacyBook);
            return ResponseEntity.ok(adaptedBook);
        }
        return ResponseEntity.notFound().build();
    }
    
    @PostMapping
    public ResponseEntity<ILibro> createLegacyBook(@RequestBody LegacyBookRequest request) {
        try {
            LegacyLibro legacyBook = new LegacyLibro(
                    idGenerator.getAndIncrement(),
                    request.getTitulo(),
                    request.getAutor(),
                    request.getTipo(),
                    request.getFormato(),
                    request.getEstado() != null ? request.getEstado() : "Disponible"
            );
            
            legacyBooks.put(legacyBook.obtenerId(), legacyBook);
            
            // Usar Adapter para retornar en formato moderno
            ILibro adaptedBook = new LegacyLibroAdapter(legacyBook);
            return ResponseEntity.ok(adaptedBook);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    @GetMapping("/raw/{id}")
    public ResponseEntity<LegacyBookResponse> getRawLegacyBook(@PathVariable Long id) {
        LegacyLibro legacyBook = legacyBooks.get(id);
        if (legacyBook != null) {
            LegacyBookResponse response = new LegacyBookResponse(
                    legacyBook.obtenerId(),
                    legacyBook.obtenerTitulo(),
                    legacyBook.obtenerAutor(),
                    legacyBook.obtenerTipo(),
                    legacyBook.obtenerFormato(),
                    legacyBook.obtenerEstado()
            );
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.notFound().build();
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLegacyBook(@PathVariable Long id) {
        LegacyLibro removedBook = legacyBooks.remove(id);
        return removedBook != null ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }
    
    // DTOs para requests y responses
    public static class LegacyBookRequest {
        private String titulo;
        private String autor;
        private String tipo;
        private String formato;
        private String estado;
        
        // Getters y setters
        public String getTitulo() { return titulo; }
        public void setTitulo(String titulo) { this.titulo = titulo; }
        public String getAutor() { return autor; }
        public void setAutor(String autor) { this.autor = autor; }
        public String getTipo() { return tipo; }
        public void setTipo(String tipo) { this.tipo = tipo; }
        public String getFormato() { return formato; }
        public void setFormato(String formato) { this.formato = formato; }
        public String getEstado() { return estado; }
        public void setEstado(String estado) { this.estado = estado; }
    }
    
    public static class LegacyBookResponse {
        private Long id;
        private String titulo;
        private String autor;
        private String tipo;
        private String formato;
        private String estado;
        
        public LegacyBookResponse(Long id, String titulo, String autor, String tipo, String formato, String estado) {
            this.id = id;
            this.titulo = titulo;
            this.autor = autor;
            this.tipo = tipo;
            this.formato = formato;
            this.estado = estado;
        }
        
        // Getters y setters
        public Long getId() { return id; }
        public void setId(Long id) { this.id = id; }
        public String getTitulo() { return titulo; }
        public void setTitulo(String titulo) { this.titulo = titulo; }
        public String getAutor() { return autor; }
        public void setAutor(String autor) { this.autor = autor; }
        public String getTipo() { return tipo; }
        public void setTipo(String tipo) { this.tipo = tipo; }
        public String getFormato() { return formato; }
        public void setFormato(String formato) { this.formato = formato; }
        public String getEstado() { return estado; }
        public void setEstado(String estado) { this.estado = estado; }
    }
}