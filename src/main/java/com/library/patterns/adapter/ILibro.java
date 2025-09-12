package com.library.patterns.adapter;

import com.library.model.Book;

// Interfaz común para libros
public interface ILibro {
    Long getId();
    String getTitle();
    String getAuthor();
    String getType();
    String getFormat();
    String getStatus();
}
