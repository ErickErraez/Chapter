package com.library.patterns.abstractfactory;

import com.library.model.Book;

public class BookEntityFactory implements EntityFactory<Book> {
    
    @Override
    public Book createEntity(String title, String author, String type, String format) {
        Book book = new Book();
        book.setTitle(title);
        book.setAuthor(author);
        book.setType(type);
        book.setFormat(format);
        book.setStatus("Available");
        return book;
    }
}