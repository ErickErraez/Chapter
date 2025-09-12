package com.library.patterns.chainofresponsibility;

public class BookValidationHandler extends ValidationHandler {

    @Override
    public void handleRequest(Book book) {
        if (isValid(book)) {
            System.out.println("Book validation passed: " + book.getTitle());
            // Pass the request to the next handler if there is one
            if (nextHandler != null) {
                nextHandler.handleRequest(book);
            }
        } else {
            System.out.println("Book validation failed: " + book.getTitle());
        }
    }

    private boolean isValid(Book book) {
        // Implement validation logic (e.g., check if title and author are not empty)
        return book.getTitle() != null && !book.getTitle().isEmpty() &&
               book.getAuthor() != null && !book.getAuthor().isEmpty();
    }
}