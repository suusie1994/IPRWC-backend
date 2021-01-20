package nl.hsleiden.kerstwebshop.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import nl.hsleiden.kerstwebshop.View;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @JsonProperty
    @JsonView(View.Public.class)
    private int id;

    @CreationTimestamp
    @Column(name = "date", nullable = false)
    @JsonProperty
    @JsonView(View.Public.class)
    private Timestamp date;

    @OneToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ElementCollection
    @CollectionTable(name = "order_details",
            joinColumns = @JoinColumn(name = "order_id",
                    referencedColumnName = "id")
    )
    @JsonProperty
    @JsonView(View.Public.class)
    private List<OrderDetail> orderitems;

    public Order() {}

    public Order(
            int id,
            Timestamp date,
            Customer customer
//            List <OrderDetail> orderitems
    ) {
        this.id = id;
        this.date = date;
        this.customer = customer;
//        this.orderitems = orderitems;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List <OrderDetail> getOrderitems() {
        return orderitems;
    }

    public void setOrderitems(List <OrderDetail> orderitems) {
        this.orderitems = orderitems;
    }
}
