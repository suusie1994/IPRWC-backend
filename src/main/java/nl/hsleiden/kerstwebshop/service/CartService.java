package nl.hsleiden.kerstwebshop.service;

import nl.hsleiden.kerstwebshop.model.Cart;
import nl.hsleiden.kerstwebshop.persistence.CartDAO;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class CartService {

    private final CartDAO dao;

    @Inject
    public CartService(CartDAO dao) {
        this.dao = dao;
    }

    public Cart[] getAllFromCustomer(int customerId) {
        return dao.findByCustomerId(customerId).toArray(new Cart[0]);
    }

    public Cart[] getAll() {
        return dao.findAll().toArray(new Cart[0]);
    }

    public Cart create(Cart cart) {
        Cart[] allFromCustomer = this.getAllFromCustomer(cart.getUserId());
        System.out.println(allFromCustomer);
        if(allFromCustomer.length >0){
            for(int i =0; i<allFromCustomer.length; i++){
                if(allFromCustomer[i].getProductId() == cart.getProductId()){
                    allFromCustomer[i].setAmount(allFromCustomer[i].getAmount() + cart.getAmount());
                    return dao.save(allFromCustomer[i]);
                }
            }
        }
        return dao.save(cart);
    }

    public Cart update(Cart cart) {
        return dao.save(cart);
    }

    public void remove(int id) {
        dao.delete(id);
    }
}
