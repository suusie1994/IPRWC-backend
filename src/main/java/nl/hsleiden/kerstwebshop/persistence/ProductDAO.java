package nl.hsleiden.kerstwebshop.persistence;

import io.dropwizard.hibernate.AbstractDAO;
import nl.hsleiden.kerstwebshop.model.Product;
import org.hibernate.SessionFactory;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;

@Singleton
public class ProductDAO extends AbstractDAO<Product> {

    @Inject
    public ProductDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public Product findById(int id) {
        return get(id);
    }

    public List<Product> findAll(){
        return query("SELECT p FROM Product p").list();
    }

    public Product save(Product product){
        return persist(product);
    }

    public void delete(Product product) {
        currentSession().delete(product);
    }
}
