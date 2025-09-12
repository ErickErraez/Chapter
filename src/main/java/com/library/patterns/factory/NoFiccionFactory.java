package com.library.patterns.factory;

import com.library.model.Book;

public class NoFiccionFactory extends BookFactory {
    public static Book createNoFiccionBook(String title, String author, String format) {
        return new Book(null, title, author, "No Ficción", format, "Disponible");
    }
}
