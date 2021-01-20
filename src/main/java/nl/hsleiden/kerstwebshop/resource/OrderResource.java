package nl.hsleiden.kerstwebshop.resource;

import com.codahale.metrics.annotation.Timed;
import com.fasterxml.jackson.annotation.JsonView;
import io.dropwizard.hibernate.UnitOfWork;
import nl.hsleiden.kerstwebshop.View;
import nl.hsleiden.kerstwebshop.model.Order;
import nl.hsleiden.kerstwebshop.service.OrderService;
import nl.hsleiden.kerstwebshop.shared.Role;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.inject.Singleton;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Singleton
@Path("/orders")
public class OrderResource {

    private final OrderService service;

    @Inject
    public OrderResource(OrderService service){
        this.service = service;
    }

    @GET
    @RolesAllowed({Role.CUSTOMER, Role.ADMIN})
    @JsonView(View.Public.class)
    @Timed
    @UnitOfWork
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Order[] getOrders() {
        return this.service.getAll();
    }

    @GET
    @Path("/{id}")
    @RolesAllowed({Role.CUSTOMER, Role.ADMIN})
    @JsonView(View.Public.class)
    @Timed
    @UnitOfWork
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Order getOrderById(@PathParam("id") int id) {
        return this.service.getById(id);
    }

    @GET
    @Path("/customer/{customerId}")
    @RolesAllowed({Role.CUSTOMER, Role.ADMIN})
    @JsonView(View.Public.class)
    @Timed
    @UnitOfWork
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Order[] getOrderByCustomerId(@PathParam("customerId") int customerId) {
        return this.service.getOrdersFromCustomer(customerId);
    }


    @PUT
    @Path("/create")
    @RolesAllowed({Role.CUSTOMER, Role.ADMIN})
    @JsonView(View.Public.class)
    @Timed
    @UnitOfWork
    public Order create(@Valid Order order) {
        return this.service.create(order);
    }

    @POST
    @Path("/update")
    @RolesAllowed({Role.CUSTOMER, Role.ADMIN})
    @JsonView(View.Public.class)
    @Timed
    @UnitOfWork
    public Order update(@Valid Order order) {
        return this.service.update(order);
    }

    @DELETE
    @Path("/{id}")
    @RolesAllowed(Role.ADMIN)
    @JsonView(View.Public.class)
    @Timed
    @UnitOfWork
    public void delete(@PathParam("id")int id) {
        this.service.remove(id);
    }

}
