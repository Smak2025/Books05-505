package ru.gr05505.ui;

public class AddBookCommand implements Command {
    private BookPresenter presenter;

    public AddBookCommand(BookPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void execute() {
        if (presenter.canAddBook())
            presenter.addBookClicked();
    }

    @Override
    public boolean canExecute() {
        return presenter.canAddBook();
    }
}
