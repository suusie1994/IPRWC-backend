package nl.hsleiden.kerstwebshop.persistence;

import io.dropwizard.hibernate.AbstractDAO;
import nl.hsleiden.kerstwebshop.model.Cart;
import org.hibernate.SessionFactory;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class CartDAO extends AbstractDAO<Cart> {

    @Inject
    public CartDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public Cart findByCustomerId(int customerId) {
        return (Cart) query("SELECT c from Cart c WHERE customer_id =" + customerId);
    }
}
