package ru.gr05505.ui;

public interface Command {
    void execute();
    boolean canExecute();
}
