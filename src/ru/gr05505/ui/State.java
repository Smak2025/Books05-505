package ru.gr05505.ui;

public record State (
        String error,
        boolean addEnabled
){
    public static State init() {
        return new State(null, false);
    }

    public static State setReady(){
        return new State(null, true);
    }
    public static State setError(String message) {
        return new State(message, false);
    }
}
