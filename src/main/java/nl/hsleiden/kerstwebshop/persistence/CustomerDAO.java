package nl.hsleiden.kerstwebshop.persistence;

import io.dropwizard.hibernate.AbstractDAO;
import nl.hsleiden.kerstwebshop.model.Customer;
import org.hibernate.SessionFactory;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;

@Singleton
public class CustomerDAO extends AbstractDAO<Customer> {

    @Inject
    public CustomerDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public Customer findById(int id) {
        return get(id);
    }

    public List<Customer> findAll(){
        return query("SELECT c FROM Customer c").list();
    }

    public Customer save(Customer customer) {
        return persist(customer);
    }

    public void delete(Customer customer) {
        currentSession().delete(customer);
    }
}
