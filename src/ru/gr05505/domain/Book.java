package ru.gr05505.domain;

import java.time.LocalDateTime;

public record Book(
        String author,
        String title,
        int year,
        String additionalInfo
) {

    public Book {
        if(author == null || author.isBlank()){
            throw new IllegalArgumentException("Нет автора!");
        }
        if(title == null || title.isBlank()) {
            throw new IllegalArgumentException("Нет названия!");
        }
        if(1500 > year){
            throw new IllegalArgumentException("Неверно указан год издания!");
        }
    }

}
