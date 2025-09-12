package com.library.patterns.strategy;

import com.library.model.Book;
import java.util.List;

public class SearchContext {
    private SearchStrategy searchStrategy;

    public SearchContext(SearchStrategy searchStrategy) {
        this.searchStrategy = searchStrategy;
    }

    public List<Book> executeSearch(List<Book> books) {
        return searchStrategy.search(books);
    }
}