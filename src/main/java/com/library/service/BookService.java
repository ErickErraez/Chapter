package com.library.service;

import com.library.model.Book;
import com.library.repository.BookRepository;
import com.library.patterns.strategy.SearchContext;
import com.library.patterns.strategy.SearchStrategy;

import java.util.List;

public class BookService {
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public void addBook(Book book) {
        bookRepository.save(book);
    }

    public List<Book> listBooks() {
        return bookRepository.findAll();
    }

    public Book findBookById(Long id) {
        return bookRepository.findById(id).orElse(null);
    }

    public List<Book> searchBooks(SearchStrategy strategy) {
        SearchContext context = new SearchContext(strategy);
        return context.executeSearch(bookRepository.findAll());
    }
}