package ru.gr05505.ui;

import ru.gr05505.domain.Book;

import java.util.List;
import java.util.Scanner;

public class Console implements BookView {

    private final BookPresenter presenter;
    private final Scanner scanner;

    private String author;
    private String title;
    private String year;
    private String additional;

    public Console(BookPresenter presenter) {
        this.presenter = presenter;
        scanner = new Scanner(System.in);
    }

    @Override
    public String getAuthor() {
        return author;
    }

    @Override
    public String getBookTitle() {
        return title;
    }

    @Override
    public String getYear() {
        return year;
    }

    @Override
    public String getAdditional() {
        return additional;
    }

    @Override
    public void clearInput() {
        author = null;
        title = null;
        year = null;
        additional = null;
    }

    @Override
    public void showBooks(List<Book> books) {
        StringBuilder sb = new StringBuilder();
        for (Book book : books) {
            sb.append(book.author());
            sb.append(" ");
            sb.append(book.title());
            sb.append(". ");
            sb.append(book.year());
            sb.append(". ");
            sb.append(book.additionalInfo());
            sb.append("\n");
        }
        System.out.println(sb);
    }

    @Override
    public void showError(String message) {
        System.err.println(message);
    }

    @Override
    public void render(State state) {
        if (!state.addEnabled()) {
            requestUserData();
        }
        if (state.error() != null) {
            showError(state.error());
        }
    }

    private void requestUserData() {
        System.out.println("Введите имя автора: ");
        author = scanner.nextLine();

        System.out.println("Введите название книги: ");
        title = scanner.nextLine();

        System.out.println("Введите год публикации: ");
        year = scanner.nextLine();

        System.out.println("Введите дополнительную информацию: ");
        additional = scanner.nextLine();

        presenter.inputChanged();
        presenter.addBookClicked();
    }
}
