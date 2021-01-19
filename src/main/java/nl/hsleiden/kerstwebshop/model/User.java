package nl.hsleiden.kerstwebshop.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import org.hibernate.validator.constraints.Length;
import nl.hsleiden.kerstwebshop.View;

import javax.persistence.*;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="users")
public class User implements Principal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", nullable = false)
    @JsonProperty
    @JsonView(View.Public.class)
    private int id;

    @Basic
    @Column(name = "username", nullable = false, unique = true)
    @Length(min = 1, max = 255)
    @JsonProperty
    @JsonView(View.Public.class)
    private String username;

    @Basic
    @Column(name = "password", nullable = false)
    @Length(min = 1, max = 255)
    @JsonProperty
    @JsonView(View.Internal.class)
    private String password;


    @ElementCollection(fetch = FetchType.EAGER)
    private List <String> roles;

//    @OneToOne
//    @JoinTable(name = "customers_users",
//            joinColumns = { @JoinColumn(name = "user_id")},
//            inverseJoinColumns = {@JoinColumn(name = "customer_id")})
//    private Customer customer;

//    @OneToMany
//    @JoinTable(name = "cart",
//            joinColumns = {@JoinColumn(name = "user_id")},
//            inverseJoinColumns = {@JoinColumn(name = "id")})
//    private List<Cart> cartItems;

    public User() {
        //
    }

    public User(String username, String password, List <String> roles) {
        this.username = username;
        this.password = password;
        this.roles = roles;
    }

    @Override
    @JsonIgnore
    @Transient
    public String getName() {
        return this.username;
    }

    // The getters and setters for all the properties in the user table
    public void setUsername() {
        this.username = username;
    }

    public int getId() {
        return id;
    }

    public void setId() {
        this.id = id;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public List <String> getRoles() {
        return roles;
    }


    public void setRoles(List <String> roles) {
        this.roles= roles;
    }

//    public Customer getCustomer() {
//        return customer;
//    }
//
//    public void setCustomer(Customer customer) {
//        this.customer = customer;
//    }

//    public List <Cart> getCartItems() {
//        return cartItems;
//    }
//
//    public void setCartItems(List <Cart> cartItems) {
//        this.cartItems = cartItems;
//    }

    public boolean hasRole(String roleName) {

        return roles.contains(roleName);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof User)) {
            return false;
        }
        return ((User) obj).getId() == this.getId();
    }
}
