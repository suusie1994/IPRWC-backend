package nl.hsleiden.kerstwebshop.persistence;

import io.dropwizard.hibernate.AbstractDAO;
import nl.hsleiden.kerstwebshop.model.Order;
import org.hibernate.SessionFactory;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;

@Singleton
public class OrderDAO extends AbstractDAO<Order> {

    @Inject
    public OrderDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public Order findById(int id) {
        return get(id);
    }

    public List<Order> findAll() {
        return query("SELECT o FROM Order o").list();
    }

    public List<Order> findOrdersOfCustomer(int customerId){
        return query("SELECT o FROM Order o WHERE customer_id = " + customerId).list();
    }

    public Order save(Order order) {
        return persist(order);
    }

    public void delete(Order order) {
        currentSession().delete(order);
    }
}
