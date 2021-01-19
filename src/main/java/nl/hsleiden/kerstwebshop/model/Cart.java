package nl.hsleiden.kerstwebshop.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import nl.hsleiden.kerstwebshop.View;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "cart")
public class Cart implements Serializable {

    @Id
    @Column(name = "product_id", nullable = false)
    @JsonProperty
    @JsonView(View.Public.class)
    private int productId;

    @Id
    @Column(name = "user_id", nullable = false)
    @JsonProperty
    @JsonView(View.Public.class)
    private int userId;

    @Column(name = "amount", nullable = false)
    @JsonProperty
    @JsonView(View.Public.class)
    private int amount;

    public Cart() {}

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Cart)) {
            return false;
        }
        return ((Cart) obj).getProductId() == this.getProductId()
                && ((Cart) obj).getUserId() == this.getUserId();
    }
}
