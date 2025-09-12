package com.library.patterns.decorator;

import com.library.model.Book;

public class PrestamoDecorator extends BookDecorator {
    private boolean isLoaned;

    public PrestamoDecorator(Book book) {
        super(book);
        this.isLoaned = "Prestado".equals(book.getStatus());
    }

    @Override
    public String getDescription() {
        return super.getDescription() + (isLoaned ? ", Prestado" : ", Disponible");
    }

    public boolean isLoaned() {
        return isLoaned;
    }

    public void loan() {
        this.isLoaned = true;
        decoratedBook.setStatus("Prestado");
    }

    public void returnBook() {
        this.isLoaned = false;
        decoratedBook.setStatus("Disponible");
    }
}
