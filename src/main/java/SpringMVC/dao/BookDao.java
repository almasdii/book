package SpringMVC.dao;

import SpringMVC.entity.Book;
import SpringMVC.entity.Person;
import SpringMVC.exception.DataBaseException;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class BookDao {
    private static final String FIND_ALL_QUERY = """
            SELECT b FROM Book b
            LEFT JOIN FETCH b.person
            """;
    private static final String FIND_BY_ID = """
            SELECT b FROM Book b 
            LEFT JOIN FETCH b.person
            WHERE b.id = :id
            """;
    private static final String ID = "id";
    private final SessionFactory sessionFactory;

    @Autowired
    public BookDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<Book> findAll() {
        try{
            Session currentSession = sessionFactory.getCurrentSession();
            currentSession.beginTransaction();
            List<Book> list = currentSession.createQuery(FIND_ALL_QUERY, Book.class).list();
            currentSession.getTransaction().commit();
            return list;
        }catch (HibernateException exception){
            throw new DataBaseException(exception);
        }
    }

    public Book save(Book book) {
        try{
            Session currentSession = sessionFactory.getCurrentSession();
            currentSession.beginTransaction();
            currentSession.persist(book);
            currentSession.getTransaction().commit();
            return book;
        }catch (HibernateException exception){
            throw new DataBaseException(exception);
        }
    }

    public Optional<Book> find(Long id) {
        try{
            Session currentSession = sessionFactory.getCurrentSession();
            currentSession.beginTransaction();
            Optional<Book> book = currentSession.createQuery(FIND_BY_ID, Book.class)
                    .setParameter(ID, id)
                    .uniqueResultOptional();
            currentSession.getTransaction().commit();
            return book;
        }catch (HibernateException exception){
            throw new DataBaseException(exception);
        }
    }

    public void selectPerson(Long bookId, Person person) {
        try{
            Session currentSession = sessionFactory.getCurrentSession();
            currentSession.beginTransaction();
            Book currentBook = currentSession.find(Book.class, bookId);
            currentBook.setPerson(person);
            currentSession.getTransaction().commit();
        }catch (HibernateException exception){
            throw new DataBaseException(exception);
        }
    }

    public void removePersonId(Long id) {
        try{
            Session currentSession = sessionFactory.getCurrentSession();
            currentSession.beginTransaction();
            Book currentBook = currentSession.find(Book.class, id);
            currentBook.setPerson(null);
            currentSession.getTransaction().commit();
        }catch (HibernateException exception){
            throw new DataBaseException(exception);
        }
    }
}
