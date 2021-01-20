package nl.hsleiden.kerstwebshop.service;

import nl.hsleiden.kerstwebshop.model.Product;
import nl.hsleiden.kerstwebshop.persistence.ProductDAO;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class ProductService {
    private final ProductDAO dao;

    @Inject
    public ProductService(ProductDAO dao) {
        this.dao = dao;
    }

    public Product create(Product product) {
        return dao.save(product);
    }

    public Product getById(int id) {
        return dao.findById(id);
    }

    public Product[] getAll() {
        return dao.findAll().toArray(new Product[0]);
    }

    public Product update(Product product) {
        return dao.save(product);
    }

    public void remove(int id) {
        Product product = this.getById(id);
        dao.delete(product);
    }
}
