package nl.hsleiden.kerstwebshop.persistence;

import io.dropwizard.hibernate.AbstractDAO;
import nl.hsleiden.kerstwebshop.model.OrderDetail;
import org.hibernate.SessionFactory;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

@Singleton
public class OrderDetailDAO extends AbstractDAO <OrderDetail> {

    @Inject
    public OrderDetailDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public OrderDetail findByOrderIdAndProductId(int orderId, int productId) {
        OrderDetail[] orderDetailsOfOrder = findOrderDetailsOfOrderId(orderId).toArray(new OrderDetail[0]);
        OrderDetail orderDetailWithProductId = new OrderDetail();
        for(int i=0; i<orderDetailsOfOrder.length;i++){
            if (orderDetailsOfOrder[i].getProductId() == productId){
                orderDetailWithProductId = orderDetailsOfOrder[i];
            }
        }
        return orderDetailWithProductId;
    }
    public List <OrderDetail> findAll() {
        return query("SELECT o FROM OrderDetail o").list();
    }

    public List<OrderDetail> findOrderDetailsOfOrderId(int orderId){
        return query("SELECT o FROM OrderDetail o WHERE order_id = " + orderId).list();
    }

    public OrderDetail save(OrderDetail orderDetail) {
        System.out.println("TEST");
        return persist(orderDetail);
    }

    public void delete(OrderDetail orderDetail) {
        currentSession().delete(orderDetail);
    }
}
