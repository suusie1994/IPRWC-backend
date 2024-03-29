package nl.hsleiden.kerstwebshop.service;

import nl.hsleiden.kerstwebshop.model.Customer;
import nl.hsleiden.kerstwebshop.persistence.CustomerDAO;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class CustomerService {

    private final CustomerDAO dao;

    @Inject
    public CustomerService(CustomerDAO dao) {
        this.dao = dao;
    }

    public Customer create(Customer customer){
        return dao.save(customer);
    }

    public Customer getById(int id) {
        return dao.findById(id);
    }

    public Customer[] getAll() {
        return dao.findAll().toArray(new Customer[0]);
    }

    public Customer update(Customer customer) {
        Customer updatedCustomer = getById(customer.getId());
        updatedCustomer.setFirstname(customer.getFirstname());
        updatedCustomer.setLastname(customer.getLastname());
        updatedCustomer.setAddress(customer.getAddress());
        updatedCustomer.setZipcode(customer.getZipcode());
        updatedCustomer.setCity(customer.getCity());
        updatedCustomer.setPhoneNumber(customer.getPhoneNumber());
        updatedCustomer.setEmailAddress(customer.getEmailAddress());
        return dao.save(updatedCustomer);
    }

    public void remove(int id) {
        Customer customer = this.getById(id);
        dao.delete(customer);
    }
}
