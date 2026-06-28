package SpringMVC.dao;

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
public class PersonDao {
    private static final String FIND_ALL_QUERY = """
            SELECT p FROM Person p
            LEFT JOIN FETCH p.books
            """;
    private static final String FIND_QUERY = """
            SELECT p FROM Person p
            LEFT JOIN FETCH p.books
            WHERE p.id = :id
            """;
    private static final String ID = "id";
    private final SessionFactory sessionFactory;

    @Autowired
    public PersonDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<Person> findAll() {
        try{
            Session currentSession = sessionFactory.getCurrentSession();
            currentSession.beginTransaction();
            List<Person> list = currentSession.createQuery(FIND_ALL_QUERY, Person.class).list();
            currentSession.getTransaction().commit();
            return list;
        }catch (HibernateException exception){
            throw new DataBaseException(exception);
        }
    }

    public void save(Person person) {
        try{
            Session currentSession = sessionFactory.getCurrentSession();
            currentSession.beginTransaction();
            currentSession.persist(person);
            currentSession.getTransaction().commit();
        }catch (HibernateException exception){
            throw new DataBaseException(exception);
        }
    }

    public Optional<Person> find(Long id) {
        try{
            Session currentSession = sessionFactory.getCurrentSession();
            currentSession.beginTransaction();
            Optional<Person> person =currentSession
                            .createQuery(FIND_QUERY,Person.class)
                            .setParameter(ID,id).uniqueResultOptional();
            currentSession.getTransaction().commit();
            return person;
        }catch (HibernateException exception){
            throw new DataBaseException(exception);
        }
    }

    public void update(Long id, Person person) {
        try{
            Session currentSession = sessionFactory.getCurrentSession();
            currentSession.beginTransaction();
            Person currentPerson = currentSession.find(Person.class, id);
            currentPerson.setName(person.getName());
            currentPerson.setBorn(person.getBorn());
            currentSession.getTransaction().commit();
        }catch (HibernateException exception){
            throw new DataBaseException(exception);
        }
    }

    public void delete(Long id) {
        try{
            Session currentSession = sessionFactory.getCurrentSession();
            currentSession.beginTransaction();
            Person person = currentSession.find(Person.class, id);
            currentSession.remove(person);
            currentSession.getTransaction().commit();
        }catch (HibernateException exception){
            throw new DataBaseException(exception);
        }
    }
}
