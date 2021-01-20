package nl.hsleiden.kerstwebshop.service;

import nl.hsleiden.kerstwebshop.model.User;
import nl.hsleiden.kerstwebshop.persistence.UserDAO;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.NotFoundException;
import java.util.Collection;
import java.util.Optional;

@Singleton
public class UserService {

    private final UserDAO dao;

    @Inject
    public UserService(UserDAO dao) {
        this.dao = dao;
    }

    public Collection<User> getAll() {
        return dao.findAll();
    }

    public User create(User user) {
        return dao.save(user);
    }

    public User update(User user) {
        return dao.save(user);
    }
    public void remove(int id) {
        User user = this.getUserById(id);
        dao.delete(user);
    }

    public User getUserById(int id) {
        Optional<User> user = dao.findById(id);
        if (!user.isPresent()) {
            throw new NotFoundException("User not found.");
        }
        return user.get();
    }
}
