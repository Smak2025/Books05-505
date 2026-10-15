package ru.gr05505.application;

import ru.gr05505.domain.Book;
import ru.gr05505.domain.BookRepository;
import ru.gr05505.ui.ParsedBookInput;

import java.time.Clock;
import java.time.LocalDateTime;
import java.util.List;

public class BookService {
    private final BookRepository repository;
    private final Clock clock;

    public BookService(BookRepository repository, Clock clock){
        this.repository = repository;
        this.clock = clock;
    }
    public void addBook(ParsedBookInput parsedBook){
        var author = parsedBook.author();
        var title = parsedBook.title();
        var year = parsedBook.year();
        var additionalInfo = parsedBook.additional();

        if(year > LocalDateTime.now(clock).getYear()){
            throw new IllegalArgumentException("Неверно указан год издания!");
        }

        var book = new Book(author,title,year,additionalInfo);
        repository.save(book);
    }

    public List<Book> getAllBooks(){
        return repository.findAllBooks();
    }

}
