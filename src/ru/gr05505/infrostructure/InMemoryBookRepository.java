package ru.gr05505.infrostructure;

import ru.gr05505.domain.Book;
import ru.gr05505.domain.BookRepository;

import java.util.ArrayList;
import java.util.List;

public class InMemoryBookRepository implements BookRepository {
    private final List<Book> bookList = new ArrayList<>();
    @Override
    public void save(Book book) {
        bookList.add(book);
    }

    @Override
    public List<Book> findAllBooks() {
        return List.copyOf(bookList);
    }

}
