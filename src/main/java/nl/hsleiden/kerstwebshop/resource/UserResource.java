package nl.hsleiden.kerstwebshop.resource;

import com.fasterxml.jackson.annotation.JsonView;
import io.dropwizard.auth.Auth;
import io.dropwizard.hibernate.UnitOfWork;
import io.dropwizard.jersey.params.IntParam;
import nl.hsleiden.kerstwebshop.model.User;
import nl.hsleiden.kerstwebshop.persistence.UserDAO;
import nl.hsleiden.kerstwebshop.service.UserService;
import nl.hsleiden.kerstwebshop.View;

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
}
