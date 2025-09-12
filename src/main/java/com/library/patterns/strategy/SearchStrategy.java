package com.library.patterns.strategy;

import com.library.model.Book;
import java.util.List;

public interface SearchStrategy {
    List<Book> search(List<Book> books);
}