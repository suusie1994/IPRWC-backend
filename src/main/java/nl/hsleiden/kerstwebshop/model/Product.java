package nl.hsleiden.kerstwebshop.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import nl.hsleiden.kerstwebshop.View;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @Column(name = "id")
    @JsonProperty
    @JsonView(View.Public.class)
    @NotNull
    private int id;

    @Column(name = "amount", nullable = false)
    @JsonProperty
    @JsonView(View.Public.class)
    private int amount;

    @Column(name = "name", nullable = false)
    @Length(min = 4, max = 255)
    @JsonProperty
    @JsonView(View.Public.class)
    @NotNull
    private String name;

    @Column(name= "description", nullable = false)
    @Length(min = 10, max = 255)
    @JsonProperty
    @JsonView(View.Public.class)
    @NotNull
    private String description;

    @Column(name= "price", nullable = false)
    @Length(min = 3, max = 8)
    @JsonProperty
    @JsonView(View.Public.class)
    @NotNull
    private String price;

    @Column(name= "image", nullable = false)
    @Length(min = 1, max = 255)
    @JsonProperty
    @URL
    @JsonView(View.Public.class)
    @NotNull
    private String image;

    public Product() { }

    public Product(int id, int amount, String name, String description, String price, String image) {
        this.id = id;
        this.amount = amount;
        this.name = name;
        this.description = description;
        this.price = price;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
