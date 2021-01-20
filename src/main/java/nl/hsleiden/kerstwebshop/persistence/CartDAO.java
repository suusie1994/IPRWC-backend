package nl.hsleiden.kerstwebshop.persistence;

import io.dropwizard.hibernate.AbstractDAO;
import nl.hsleiden.kerstwebshop.model.Cart;
import org.hibernate.SessionFactory;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;

@Singleton
public class CartDAO extends AbstractDAO<Cart> {

    @Inject
    public CartDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public List<Cart> findByCustomerId(int customerId) {
        return query("SELECT c FROM Cart c WHERE customer_id =" + customerId).list();
    }

    public List<Cart> findAll() {
        return query("SELECT c FROM Cart c").list();
    }

    public Cart save(Cart cart) {
        return persist(cart);
    }

    public void delete(Cart cart) {
        currentSession().delete(cart);
    }
}
