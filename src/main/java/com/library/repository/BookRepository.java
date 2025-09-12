package com.library.repository;

import com.library.model.Book;
import java.util.List;

public interface BookRepository {
    void save(Book book);
    void update(Book book);
    void delete(Long id);
    Book findById(Long id);
    List<Book> findAll();
}