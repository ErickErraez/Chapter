package com.library.patterns.builder;

import com.library.model.Book;

public class BookBuilder {
    private String title;
    private String author;
    private String type; // Fiction or Non-Fiction
    private String format; // Physical or Digital
    private String status; // Available or Loaned

    public BookBuilder setTitle(String title) {
        this.title = title;
        return this;
    }

    public BookBuilder setAuthor(String author) {
        this.author = author;
        return this;
    }

    public BookBuilder setType(String type) {
        this.type = type;
        return this;
    }

    public BookBuilder setFormat(String format) {
        this.format = format;
        return this;
    }

    public BookBuilder setStatus(String status) {
        this.status = status;
        return this;
    }

    public Book build() {
        return new Book(title, author, type, format, status);
    }
}