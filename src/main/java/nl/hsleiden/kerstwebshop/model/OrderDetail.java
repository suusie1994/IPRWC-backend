package nl.hsleiden.kerstwebshop.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import nl.hsleiden.kerstwebshop.View;

import javax.persistence.*;

@Embeddable
public class OrderDetail {

    @Column(name = "product_id", nullable = false)
    @JsonProperty
    @JsonView(View.Public.class)
    private int productId;

    @Column(name = "order_id", nullable = false)
    @JsonProperty
    @JsonView(View.Public.class)
    private int orderId;

    @Column(name = "amount", nullable = false)
    @JsonProperty
    @JsonView(View.Public.class)
    private int amount;

    public OrderDetail() {
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
