package SpringMVC.dao;

import SpringMVC.entity.Person;
import SpringMVC.exception.DataBaseException;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PersonDao {
    private static final String FIND_ALL_QUERY = """
            SELECT p FROM Person p
            """;
    private final SessionFactory factory;
    private final SessionFactory sessionFactory;

    @Autowired
    public PersonDao(SessionFactory factory, SessionFactory sessionFactory) {
        this.factory = factory;
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
}
