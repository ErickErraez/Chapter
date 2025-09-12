package com.library.patterns.strategy;

import com.library.model.Book;
import java.util.List;
import java.util.stream.Collectors;

public class SearchByTitle implements SearchStrategy {
    private String title;

    public SearchByTitle(String title) {
        this.title = title;
    }

    @Override
    public List<Book> search(List<Book> books) {
        return books.stream()
                .filter(book -> book.getTitle().equalsIgnoreCase(title))
                .collect(Collectors.toList());
    }
}