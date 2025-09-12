package com.library.patterns.abstractfactory;

import com.library.model.Book;

public class LibroDigitalFactory implements EntityFactory {
    @Override
    public Book create(String title, String author, String type) {
        return new Book(null, title, author, type, "Digital", "Disponible");
    }
}
