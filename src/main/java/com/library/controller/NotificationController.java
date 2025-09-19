package com.library.controller;

import com.library.patterns.observer.LibraryObserver;
import com.library.patterns.observer.NotificationService;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.*;

@RestController
@RequestMapping("/api/notifications")
@CrossOrigin(origins = "*")
public class NotificationController {
    
    private final NotificationService notificationService = new NotificationService();
    private final List<String> messageHistory = new ArrayList<>();
    
    public NotificationController() {
        // Registrar observadores por defecto
        notificationService.registerObserver(new LibraryObserver("Admin"));
        notificationService.registerObserver(new LibraryObserver("Bibliotecario"));
        
        // Agregar observador personalizado que guarda el historial
        notificationService.registerObserver(message -> {
            messageHistory.add(new Date() + ": " + message);
        });
    }
    
    @PostMapping("/send")
    public ResponseEntity<String> sendNotification(@RequestBody NotificationRequest request) {
        try {
            notificationService.notifyObservers(request.getMessage());
            return ResponseEntity.ok("Notificación enviada exitosamente");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al enviar notificación: " + e.getMessage());
        }
    }
    
    @PostMapping("/observers")
    public ResponseEntity<String> addObserver(@RequestBody ObserverRequest request) {
        try {
            LibraryObserver observer = new LibraryObserver(request.getName());
            notificationService.registerObserver(observer);
            return ResponseEntity.ok("Observador '" + request.getName() + "' agregado exitosamente");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al agregar observador: " + e.getMessage());
        }
    }
    
    @GetMapping("/history")
    public ResponseEntity<List<String>> getNotificationHistory() {
        return ResponseEntity.ok(new ArrayList<>(messageHistory));
    }
    
    @DeleteMapping("/history")
    public ResponseEntity<String> clearHistory() {
        messageHistory.clear();
        return ResponseEntity.ok("Historial de notificaciones limpiado");
    }
    
    @GetMapping("/test")
    public ResponseEntity<String> testNotifications() {
        notificationService.notifyObservers("Mensaje de prueba del sistema de notificaciones");
        return ResponseEntity.ok("Notificación de prueba enviada");
    }
    
    // DTOs para requests
    public static class NotificationRequest {
        private String message;
        
        public String getMessage() { return message; }
        public void setMessage(String message) { this.message = message; }
    }
    
    public static class ObserverRequest {
        private String name;
        
        public String getName() { return name; }
        public void setName(String name) { this.name = name; }
    }
}