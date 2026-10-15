package ru.gr05505.ui;

import ru.gr05505.application.BookService;

public class BookPresenter {
    private final BookService service;
    private BookView view;

    private final BookInputParser parser;

    public BookPresenter(BookService service, BookInputParser parser) {
        this.service = service;
        this.parser = parser;
    }

    public void attachView(BookView view) {
        this.view = view;
    }

    public void addBookClicked(){
        try {
            var author = view.getAuthor();
            var title = view.getBookTitle();
            var year = view.getYear();
            var additional = view.getAdditional();

            ParsedBookInput parsedBook =  parser.parse(author, title, year, additional);

            service.addBook(parsedBook);
            view.clearInput();
            view.showBooks(service.getAllBooks());
            view.render(State.init());
        } catch (IllegalArgumentException e) {
            view.render(State.setError(e.getMessage()));
        }
    }

    public boolean canAddBook() {
        return isNotBlank(view.getAuthor()) &&
                isNotBlank(view.getBookTitle()) &&
                isNotBlank(view.getYear());
    }

    private boolean isNotBlank(String value){
        return value != null && !value.isBlank();
    }

    public void viewOpened() {
        view.showBooks(service.getAllBooks());
        view.render(State.init());
    }

    public void inputChanged(){
        if (canAddBook()) {
            view.render(State.setReady());
        }
        else {
            view.render(State.init());
        }
    }

}
