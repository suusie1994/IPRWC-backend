package nl.hsleiden.kerstwebshop.service;

import nl.hsleiden.kerstwebshop.model.OrderDetail;
import nl.hsleiden.kerstwebshop.persistence.OrderDetailDAO;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class OrderDetailService {
    private final OrderDetailDAO dao;

    @Inject
    public OrderDetailService(OrderDetailDAO dao) {
        this.dao = dao;
    }

    public OrderDetail create(OrderDetail orderDetail){
        return dao.save(orderDetail);
    }

    public OrderDetail getByOrderIdAndProductId(int orderId, int productId) {
        return dao.findByOrderIdAndProductId(orderId, productId);
    }

    public OrderDetail[] getAll() {
        return dao.findAll().toArray(new OrderDetail[0]);
    }

    public OrderDetail[] getOrderDetailsOfOrderId(int orderId) {
        return dao.findOrderDetailsOfOrderId(orderId).toArray(new OrderDetail[0]);
    }

    public OrderDetail update(OrderDetail orderDetail) {
        OrderDetail[] oldOrderDetails = getOrderDetailsOfOrderId(orderDetail.getOrderId());
        OrderDetail updatedOrderDetail = new OrderDetail();
        for(int i=0; i<oldOrderDetails.length;i++) {
            if (oldOrderDetails[i].getProductId() == orderDetail.getProductId()) {
                updatedOrderDetail.setAmount(oldOrderDetails[i].getAmount());
            }
        }
        return dao.save(updatedOrderDetail);
    }

    public void remove(OrderDetail orderDetail) {
        dao.delete(orderDetail);
    }
}
