package nl.hsleiden.kerstwebshop.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import nl.hsleiden.kerstwebshop.View;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "customers")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @JsonProperty
    @JsonView(View.Public.class)
    private int id;

    @Column(name = "firstname", nullable = false)
    @Length(min = 1, max = 255)
    @JsonProperty
    @JsonView(View.Public.class)
    private String firstname;

    @Column(name = "lastname", nullable = false)
    @Length(min = 1, max = 255)
    @JsonProperty
    @JsonView(View.Public.class)
    private String lastname;

    @Column(name = "emailAddress", nullable = false)
    @Length(min = 1, max = 255)
    @JsonProperty
    @JsonView(View.Public.class)
    private String emailAddress;

    @Column(name = "address", nullable = false)
    @Length(min = 1, max = 255)
    @JsonProperty
    @JsonView(View.Public.class)
    private String address;

    @Column(name = "zipcode", nullable = false)
    @Length(min = 1, max = 7)
    @JsonProperty
    @JsonView(View.Public.class)
    private String zipcode;

    @Column(name = "city", nullable = false)
    @Length(min = 1, max = 255)
    @JsonProperty
    @JsonView(View.Public.class)
    private String city;

    @Column(name = "phoneNumber", nullable = false)
    @Length(min = 1, max = 15)
    @JsonProperty
    @JsonView(View.Public.class)
    private String phoneNumber;

//    @OneToOne
//    @JoinTable(name = "customers_users",
//            joinColumns = { @JoinColumn(name = "customer_id")},
//            inverseJoinColumns = {@JoinColumn(name = "user_id")})
//    @JsonIgnore
//    private User user;

//    @OneToMany
//    @JoinTable(name = "orders",
//            joinColumns = {@JoinColumn(name = "customer_id")},
//            inverseJoinColumns = {@JoinColumn(name = "id")})
//    @JsonIgnore
//    private List<Order> orders;

    public Customer() {}

    public Customer(
            String firstname,
            String lastname,
            String emailAddress,
            String address,
            String zipcode,
            String city,
            String phoneNumber){
         this.firstname = firstname;
         this.lastname = lastname;
         this.emailAddress = emailAddress;
         this.address = address;
         this.zipcode = zipcode;
         this.city = city;
         this.phoneNumber = phoneNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
