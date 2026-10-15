package ru.gr05505.domain;

import java.util.List;

public interface BookRepository {
    void save(Book book);
    List<Book> findAllBooks();

}
