package nl.hsleiden.kerstwebshop.auth;

import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.Authenticator;
import io.dropwizard.auth.basic.BasicCredentials;
import io.dropwizard.hibernate.UnitOfWork;
import nl.hsleiden.kerstwebshop.model.User;
import nl.hsleiden.kerstwebshop.persistence.UserDAO;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Optional;

@Singleton
public class UserAuthenticator implements Authenticator<BasicCredentials, User> {
    private final UserDAO dao;

    @Inject
    public UserAuthenticator(UserDAO dao) {
        this.dao = dao;
    }

    @Override
    @UnitOfWork
    public Optional<User> authenticate(BasicCredentials credentials) throws AuthenticationException {
        User user =  dao.findByUsername(credentials.getUsername());
        if (user == null) {
            return Optional.empty();
        }
        if (!user.getPassword().equals(credentials.getPassword())) {
            return Optional.empty();
        }
        return Optional.of(user);
    }
}
