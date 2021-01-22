package nl.hsleiden.kerstwebshop.resource;

import com.codahale.metrics.annotation.Timed;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import io.dropwizard.hibernate.UnitOfWork;
import nl.hsleiden.kerstwebshop.View;
import nl.hsleiden.kerstwebshop.model.Customer;
import nl.hsleiden.kerstwebshop.model.User;
import nl.hsleiden.kerstwebshop.service.CustomerService;
import nl.hsleiden.kerstwebshop.shared.Role;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.inject.Singleton;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Singleton
@Path("customers")
@Produces(MediaType.APPLICATION_JSON)
public class CustomerResource {

    private final CustomerService service;

    @Inject
    public CustomerResource(CustomerService service) {
        this.service = service;
    }

    @GET
    @RolesAllowed(Role.ADMIN)
    @JsonView(View.Public.class)
    @Timed
    @UnitOfWork
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Customer[] getCustomers(){
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
    public Customer getCustomerById(@PathParam("id") int id) {
        return this.service.getById(id);
    }

    @PUT
    @Path("/create")
    @JsonView(View.Public.class)
    @Timed
    @UnitOfWork
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Customer create(@Valid Customer customer) {
        //userid ophalen??
        return this.service.create(customer);
    }

    @POST
    @Path("/update")
    @RolesAllowed({Role.ADMIN, Role.CUSTOMER})
    @JsonView(View.Public.class)
    @Timed
    @UnitOfWork
    public Customer update(@Valid Customer customer) {
        return this.service.update(customer);
    }

    @DELETE
    @Path("/{id}")
    @RolesAllowed({Role.ADMIN, Role.CUSTOMER})
    @JsonView(View.Public.class)
    @Timed
    @UnitOfWork
    public void delete(@PathParam("id") int id) {
        this.service.remove(id);
    }
}
