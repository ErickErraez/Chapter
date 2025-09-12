package com.library.patterns.factory;

import com.library.model.Book;

public class BookFactory {
    
    public static Book createBook(String title, String author, String type, String format) {
        Book book = new Book();
        book.setTitle(title);
        book.setAuthor(author);
        book.setType(type);
        book.setFormat(format);
        book.setStatus("Available");
        return book;
    }
}