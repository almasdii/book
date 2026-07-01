package SpringMVC.service;

import SpringMVC.dao.BookDao;
import SpringMVC.dao.PersonDao;
import SpringMVC.dto.BookDetailsView;
import SpringMVC.dto.BookSummaryView;
import SpringMVC.entity.Book;
import SpringMVC.entity.Person;
import SpringMVC.exception.BookNotFoundException;
import SpringMVC.exception.PersonNotFoundException;
import SpringMVC.mapper.BookMapper;
import SpringMVC.mapper.BookMapperImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class BookService {
    private final BookDao bookDao;
    private final BookMapper mapper;
    private final PersonDao personDao;

    @Autowired
    public BookService(BookDao bookDao, BookMapperImpl mapper, PersonDao personDao) {
        this.bookDao = bookDao;
        this.mapper = mapper;
        this.personDao = personDao;
    }

    public List<BookSummaryView> findAll() {
        List<Book> all = bookDao.findAll();
        return mapper.fromBookListToBookSummaryViewList(all);
    }

    public void save(BookSummaryView bookSummaryView) {
        Book book = mapper.fromBookDtoToBook(bookSummaryView);
        Book save = bookDao.save(book);
    }

    public BookDetailsView find(Long id) {
        Book book = bookDao.find(id)
                .orElseThrow(() -> new BookNotFoundException("Book not found with this id"));
        return mapper.fromBookToBookViewDto(book);

    }

    public void selectPerson(Long bookId, Long personId) {
        log.debug("book id : {}, person id : {} ",bookId,personId);
        Person person = personDao.find(personId).orElseThrow(() -> new PersonNotFoundException("Person not found "));
        bookDao.selectPerson(bookId,person);
    }

    public void setAvailable(Long id) {
        bookDao.removePersonId(id);
    }
}

