package nl.hsleiden.kerstwebshop.auth;

import io.dropwizard.auth.Authorizer;
import nl.hsleiden.kerstwebshop.model.User;

import javax.inject.Singleton;

@Singleton
public class UserAuthorizer implements Authorizer<User> {

    @Override
    public boolean authorize(User user, String role) {
        return user.hasRole(role);

    }
}
