package nl.hsleiden.kerstwebshop.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import nl.hsleiden.kerstwebshop.View;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "cart")
public class Cart implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @JsonProperty
    @JsonView(View.Public.class)
    private int id;

    @Column(name = "product_id", nullable = false)
    @JsonProperty
    @NotNull
    @JsonView(View.Public.class)
    private int productId;

    @Column(name = "user_id", nullable = true)
    @JsonProperty
    @NotNull
    @JsonView(View.Public.class)
    private int userId;

    @Column(name = "amount", nullable = false)
    @JsonProperty
    @JsonView(View.Public.class)
    @NotNull
    private int amount;

    public Cart() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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
