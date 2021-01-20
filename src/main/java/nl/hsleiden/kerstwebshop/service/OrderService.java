package nl.hsleiden.kerstwebshop.service;

import nl.hsleiden.kerstwebshop.model.Order;
import nl.hsleiden.kerstwebshop.persistence.OrderDAO;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class OrderService {
    private final OrderDAO dao;

    @Inject
    public OrderService(OrderDAO dao) {
        this.dao = dao;
    }

    public Order create(Order order) {
        return dao.save(order);
    }

    public Order getById(int id) {
        return dao.findById(id);
    }

    public Order[] getAll() {
        return dao.findAll().toArray(new Order[0]);
    }

    public Order[] getOrdersFromCustomer(int customerId) {
        return dao.findOrdersOfCustomer(customerId).toArray(new Order[0]);
    }

    public Order update(Order order) {
        return dao.save(order);
    }

    public void remove(int id) {
        Order order = this.dao.findById(id);
        dao.delete(order);
    }

}
