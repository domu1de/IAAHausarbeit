/*
 * Copyright (c) 2014 Domenic Muskulus, Alexander Mersmann.
 * Licensed under the MIT License.
 */

package de.nak.exammgmt.service.user;

import de.nak.exammgmt.persistence.dao.user.UserDAO;
import de.nak.exammgmt.persistence.entity.user.User;
import de.nak.exammgmt.service.exception.AlreadyCreatedException;
import de.nak.exammgmt.service.exception.NotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Objects;

/**
 * Default implementation of the UserService.
 *
 * @author Domenic Muskulus <domenic@muskulus.eu>
 */
public class DefaultUserService implements UserService {

    private UserDAO userDAO;
    private PasswordEncoder passwordEncoder;

    @Override
    public void create(User user) throws AlreadyCreatedException {
        Objects.requireNonNull(user);

        if (userDAO.findByUsernameOrEmail(user.getUsername()) != null) {
            throw new AlreadyCreatedException(String.format("The username [%s] is already taken.", user.getUsername()));
        }

        if (userDAO.findByUsernameOrEmail(user.getEmail()) != null) {
            throw new AlreadyCreatedException(String.format("The email [%s] is already taken.", user.getEmail()));
        }

        if (user.getPassword() != null) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }

        user.setActivated(false);

        userDAO.save(user);
    }

    @Override
    public void update(User user) {
        Objects.requireNonNull(user);
        Objects.requireNonNull(user.getId());

        userDAO.save(user);
    }

    @Override
    public void updatePassword(User user, String password) throws NotFoundException {
        user.setPassword(passwordEncoder.encode(password));
    }

    @Override
    public void activate(User user) {
        Objects.requireNonNull(user);
        Objects.requireNonNull(user.getId());

        user.setActivated(true);
        userDAO.save(user);
    }

    @Override
    public void deactivate(User user) {
        Objects.requireNonNull(user);
        Objects.requireNonNull(user.getId());

        user.setActivated(false);
        user.setPassword(null);
        user.setCurrentUserSession(null);
        userDAO.save(user);
    }

    @Override
    public User get(long id) throws NotFoundException {
        User user = userDAO.findById(id);
        if (user == null) {
            throw new NotFoundException(User.class);
        }
        return user;
    }

    @Override
    public User get(String usernameOrEmail) throws NotFoundException {
        User user = userDAO.findByUsernameOrEmail(usernameOrEmail);
        if (user == null) {
            throw new NotFoundException(User.class);
        }
        return user;
    }

    @Override
    public List<User> list() {
        return userDAO.findAll();
    }

    @Override
    public List<User> listInactiveUsers() {
        return userDAO.findInactiveUsers();
    }

    @Override
    public List<User> listActiveUsers() {
        return userDAO.findActiveUsers();
    }

    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

}
