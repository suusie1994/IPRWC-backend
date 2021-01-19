package nl.hsleiden.kerstwebshop.persistence;

import io.dropwizard.hibernate.AbstractDAO;
import nl.hsleiden.kerstwebshop.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import javax.inject.Inject;
import java.util.*;

public class UserDAO extends AbstractDAO<User>{


    private SessionFactory sessionFactory;

    @Inject
    public UserDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public Optional<User> findById(int id) {

        return Optional.ofNullable(get(id));
    }

   public User findByUsername(String username) {
        Query query = currentSession().createQuery("SELECT u FROM User u WHERE u.username = " +
                ":username");
        query.setParameter("username", username);
        User user = (User) query.uniqueResult();

        return user;
    }

    public List<User> findAll(){
        return (query("SELECT u from User u").list());
    }

    public User save(User user) {
        return persist(user);
    }

    public void delete(User user) {
        currentSession().delete(user);
    }
}
