package nl.hsleiden.kerstwebshop.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import nl.hsleiden.kerstwebshop.View;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;

@Entity
@Table(name = "order_details")
public class OrderDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @JsonProperty
    @JsonView(View.Public.class)
    private int id;

    @Column(name = "order_id", nullable = false)
    @JsonProperty
    @JsonView(View.Public.class)
    private int orderId;

    @Column(name = "product_id", nullable = false)
    @JsonProperty
    @JsonView(View.Public.class)
    private int productId;

    @Column(name = "amount", nullable = false)
    @JsonProperty
    @JsonView(View.Public.class)
    private int amount;

    @Column(name = "customer_id")
    @JsonProperty
    @JsonView(View.Public.class)
    private int customerId;

    @Column(name = "firstname")
    @Length(min = 1, max = 255)
    @JsonProperty
    @JsonView(View.Public.class)
    private String firstname;

    @Column(name = "lastname")
    @Length(min = 1, max = 255)
    @JsonProperty
    @JsonView(View.Public.class)
    private String lastname;

    @Column(name = "emailAddress")
    @Length(min = 1, max = 255)
    @JsonProperty
    @JsonView(View.Public.class)
    private String emailAddress;

    @Column(name = "address")
    @Length(min = 1, max = 255)
    @JsonProperty
    @JsonView(View.Public.class)
    private String address;

    @Column(name = "zipcode")
    @Length(min = 1, max = 7)
    @JsonProperty
    @JsonView(View.Public.class)
    private String zipcode;

    @Column(name = "city")
    @Length(min = 1, max = 255)
    @JsonProperty
    @JsonView(View.Public.class)
    private String city;

    @Column(name = "phoneNumber")
    @Length(min = 1, max = 15)
    @JsonProperty
    @JsonView(View.Public.class)
    private String phoneNumber;

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

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
