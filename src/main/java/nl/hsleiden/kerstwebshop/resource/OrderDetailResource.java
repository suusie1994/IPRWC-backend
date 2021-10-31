package nl.hsleiden.kerstwebshop.resource;

import com.codahale.metrics.annotation.Timed;
import com.fasterxml.jackson.annotation.JsonView;
import io.dropwizard.hibernate.UnitOfWork;
import nl.hsleiden.kerstwebshop.View;
import nl.hsleiden.kerstwebshop.model.OrderDetail;
import nl.hsleiden.kerstwebshop.service.OrderDetailService;
import nl.hsleiden.kerstwebshop.shared.Role;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.inject.Singleton;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Singleton
@Path("/orderDetail")
public class OrderDetailResource {
    private final OrderDetailService service;

    @Inject
    public OrderDetailResource(OrderDetailService service) {
        this.service = service;
    }

    @GET
    @RolesAllowed({Role.CUSTOMER, Role.ADMIN})
    @JsonView(View.Public.class)
    @Timed
    @UnitOfWork
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public OrderDetail[] getAllOrderDetails() {
        return this.service.getAll();
    }

    @GET
    @RolesAllowed({Role.CUSTOMER, Role.ADMIN})
    @Path("/{orderId}")
    @JsonView(View.Public.class)
    @Timed
    @UnitOfWork
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public OrderDetail[] getAllOrderDetailsByOrderId(@PathParam("orderId") int orderId) {
        return this.service.getOrderDetailsOfOrderId(orderId);
    }

    @GET
    @RolesAllowed({Role.CUSTOMER, Role.ADMIN})
    @Path("/{orderId}/{productId}")
    @JsonView(View.Public.class)
    @Timed
    @UnitOfWork
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public OrderDetail getOrderDetailByOrderIdAndProductId(
            @PathParam("orderId") int orderId,
            @PathParam("productId") int productId){
        return this.service.getByOrderIdAndProductId(orderId,productId);
    }

    @PUT
    @Path("/create")
    @RolesAllowed({Role.CUSTOMER, Role.ADMIN})
    @JsonView(View.Public.class)
    @Timed
    @UnitOfWork
    public OrderDetail create(@Valid  OrderDetail orderDetail){
        return this.service.create(orderDetail);
    }

    @POST
    @Path("/update")
    @RolesAllowed({Role.CUSTOMER, Role.ADMIN})
    @JsonView(View.Public.class)
    @Timed
    @UnitOfWork
    public OrderDetail update(@Valid OrderDetail orderDetail){
        return this.service.update(orderDetail);
    }

    @DELETE
    @RolesAllowed(Role.ADMIN)
    @JsonView(View.Public.class)
    @Timed
    @UnitOfWork
    public void delete(@Valid OrderDetail orderDetail){
        this.service.remove(orderDetail);
    }
}
