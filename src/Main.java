import ru.gr05505.application.BookService;
import ru.gr05505.ui.BookPresenter;
import ru.gr05505.domain.BookRepository;
import ru.gr05505.infrostructure.InMemoryBookRepository;
import ru.gr05505.ui.*;

void main() {
    BookRepository repository = new InMemoryBookRepository();
    var clock = Clock.systemDefaultZone();
//    var clock = Clock.fixed(
//            Instant.parse("2027-03-05T00:00:00Z"),
//            ZoneId.systemDefault()
//    );
    BookService service = new BookService(repository, clock);
    BookInputParser parser = new BookInputParser();
    BookPresenter presenter = new BookPresenter(service, parser);

    var wnd = new Window(presenter);
    presenter.attachView(wnd);

    wnd.setVisible(true);

    presenter.viewOpened();
}