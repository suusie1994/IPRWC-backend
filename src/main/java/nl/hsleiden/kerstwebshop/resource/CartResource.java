package nl.hsleiden.kerstwebshop.resource;

import com.codahale.metrics.annotation.Timed;
import com.fasterxml.jackson.annotation.JsonView;
import io.dropwizard.hibernate.UnitOfWork;
import nl.hsleiden.kerstwebshop.View;
import nl.hsleiden.kerstwebshop.model.Cart;
import nl.hsleiden.kerstwebshop.service.CartService;
import nl.hsleiden.kerstwebshop.shared.Role;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.inject.Singleton;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Singleton
@Path("/cart")
public class CartResource {

    private final CartService service;

    @Inject
    public CartResource(CartService service) {
        this.service = service;
    }

    @GET
//    @RolesAllowed(Role.ADMIN)
    @JsonView(View.Public.class)
    @Timed
    @UnitOfWork
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Cart[] getAllItemThatAreNotFromACustomer() {
        return this.service.getAllItemThatAreNotFromACustomer();
    }

    @GET
    @Path("/{customerId}")
    @RolesAllowed({Role.ADMIN, Role.CUSTOMER})
    @JsonView(View.Public.class)
    @Timed
    @UnitOfWork
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Cart[] getAllCartItemsOfCustomer(@PathParam("customerId") int customerId) {
        return this.service.getAllFromCustomer(customerId);
    }

    @PUT
    @Path("/create")
//    @RolesAllowed({Role.ADMIN, Role.CUSTOMER})
    @JsonView(View.Public.class)
    @Timed
    @UnitOfWork
    public Cart create(@Valid Cart cart) {
        return this.service.create(cart);
    }

    @POST
    @Path("/update")
//    @RolesAllowed({Role.ADMIN, Role.CUSTOMER})
    @JsonView(View.Public.class)
    @Timed
    @UnitOfWork
    public Cart update(@Valid Cart cart) {
        return this.service.update(cart);
    }

    @DELETE
    @Path("/{id}")
//    @RolesAllowed({Role.ADMIN, Role.CUSTOMER})
    @JsonView(View.Public.class)
    @Timed
    @UnitOfWork
    public void delete(@PathParam("id")int id) {
        this.service.remove(id);
    }
}
