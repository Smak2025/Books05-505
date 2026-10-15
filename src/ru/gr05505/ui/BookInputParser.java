package ru.gr05505.ui;

import java.time.LocalDateTime;

public class BookInputParser {

    private int parseYear(String year){

        if(year == null || year.isBlank()) {
            throw new IllegalArgumentException("Нет года издания!");
        }
        int iYear;

        try {
            iYear = Integer.parseInt(year);
        }catch (NumberFormatException ex) {
            throw new IllegalArgumentException("Неверно указан год издания!", ex);
        }


        return iYear;

    }

    public ParsedBookInput parse(
            String author,
            String title,
            String year,
            String additional
    ) {

        if(author == null || author.isBlank()){
            throw new IllegalArgumentException("Нет автора!");
        }
        if(title == null || title.isBlank()) {
            throw new IllegalArgumentException("Нет названия!");
        }

        return new ParsedBookInput(author, title, parseYear(year), additional);
    }

}
