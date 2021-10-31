package nl.hsleiden.kerstwebshop.model;

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

    @Column(name = "date", nullable = false)
    @JsonProperty
    @JsonView(View.Public.class)
    private Timestamp date;

    @Column(name = "customer_id", nullable = true)
    @JsonProperty
    @JsonView(View.Public.class)
    private int customerId;

    @Column(name = "status")
    @JsonProperty
    @JsonView(View.Public.class)
    private String status;

    public Order() {}

    public Order(
            int id,
            Timestamp date,
            int customerId
    ) {
        this.id = id;
        this.date = date;
        this.customerId = customerId;
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

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
