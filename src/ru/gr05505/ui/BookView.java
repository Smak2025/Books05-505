package ru.gr05505.ui;

import ru.gr05505.domain.Book;

import java.util.List;

public interface BookView {
    String getAuthor();
    String getBookTitle();
    String getYear();
    String getAdditional();

    void clearInput();
    void showBooks(List<Book> books);
    void showError(String message);

    void render(State state);

}
