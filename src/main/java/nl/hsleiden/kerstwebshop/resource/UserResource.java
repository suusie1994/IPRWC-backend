package nl.hsleiden.kerstwebshop.resource;

import com.codahale.metrics.annotation.Timed;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import io.dropwizard.auth.Auth;
import io.dropwizard.hibernate.UnitOfWork;
import io.dropwizard.jersey.params.IntParam;
import nl.hsleiden.kerstwebshop.model.User;
import nl.hsleiden.kerstwebshop.persistence.UserDAO;
import nl.hsleiden.kerstwebshop.service.UserService;
import nl.hsleiden.kerstwebshop.View;
import nl.hsleiden.kerstwebshop.shared.Role;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.inject.Singleton;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Collection;
import java.util.Optional;

@Singleton
@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {

    private final UserService service;

    private UserDAO userDAO;

    @Inject
    public UserResource(UserService service) {
        this.service = service;
    }

    @GET
    @UnitOfWork
    @JsonView(View.Public.class)
    public Collection<User> getUsers() {
        return this.service.getAll();
    }

    @GET
    @Path("/me")
    @JsonProperty
    @JsonView(View.Protected.class)
    public User getMe(@Auth User user) {
        return user;
    }

    @POST
    @Path("/auth")
    @UnitOfWork
    @JsonView(View.Protected.class)
    public User auth(@Valid User authenticator){
        return this.userDAO.findByUsername(authenticator.getName());
    }

    @GET
    @Path("/{id}")
    @UnitOfWork
    public Optional<User> findById(@PathParam("id")IntParam id){
        return userDAO.findById(id.get());
    }

    @PUT
    @Path("/create")
    @JsonView(View.Public.class)
    @Timed
    @UnitOfWork
    public User create(@Valid User user) {
        return this.service.create(user);
    }

    @POST
    @Path("/update")
    @RolesAllowed({Role.ADMIN, Role.CUSTOMER})
    @JsonView(View.Public.class)
    @Timed
    @UnitOfWork
    public User update(@Valid User user) {
        return this.service.update(user);
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
