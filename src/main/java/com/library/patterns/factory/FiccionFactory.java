package com.library.patterns.factory;

import com.library.model.Book;

public class FiccionFactory extends BookFactory {
    public static Book createFiccionBook(String title, String author, String format) {
        return new Book(null, title, author, "Ficción", format, "Disponible");
    }
}
