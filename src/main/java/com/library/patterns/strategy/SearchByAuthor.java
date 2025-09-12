package com.library.patterns.strategy;

import com.library.model.Book;
import java.util.List;
import java.util.stream.Collectors;

public class SearchByAuthor implements SearchStrategy {
    private String author;

    public SearchByAuthor(String author) {
        this.author = author;
    }

    @Override
    public List<Book> search(List<Book> books) {
        return books.stream()
                .filter(book -> book.getAuthor().equalsIgnoreCase(author))
                .collect(Collectors.toList());
    }
}