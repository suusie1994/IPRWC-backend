package nl.hsleiden.kerstwebshop.resource;

import com.codahale.metrics.annotation.Timed;
import com.fasterxml.jackson.annotation.JsonView;
import io.dropwizard.hibernate.UnitOfWork;
import nl.hsleiden.kerstwebshop.View;
import nl.hsleiden.kerstwebshop.model.Product;
import nl.hsleiden.kerstwebshop.service.ProductService;
import nl.hsleiden.kerstwebshop.shared.Role;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.inject.Singleton;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Singleton
@Path("/products")
@Produces(MediaType.APPLICATION_JSON)
public class ProductResource {

    private final ProductService service;

    @Inject
    public ProductResource(ProductService service) {
        this.service = service;
    }

    @GET
    @JsonView(View.Public.class)
    @Timed
    @UnitOfWork
    public Product[] getProducts() {
        return this.service.getAll();
    }

    @GET
    @Path("/{id}")
    @JsonView(View.Public.class)
    @Timed
    @UnitOfWork
    public Product getProductById(@PathParam("id") int id) {
        return this.service.getById(id);
    }

    @PUT
    @Path("/create")
    @RolesAllowed(Role.ADMIN)
    @JsonView(View.Public.class)
    @Timed
    @UnitOfWork
    public Product create(@Valid Product product) {
        return this.service.create(product);
    }

    @POST
    @Path("/update")
//    @RolesAllowed({Role.ADMIN, Role.CUSTOMER})
    @JsonView(View.Public.class)
    @Timed
    @UnitOfWork
    public Product update(@Valid Product product) {
        return this.service.update(product);
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
