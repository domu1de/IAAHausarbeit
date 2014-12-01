/*
 * Copyright (c) 2014 Domenic Muskulus, Alexander Mersmann.
 * Licensed under the MIT License.
 */

package de.nak.nakexammgmt.service.user;

import de.nak.nakexammgmt.persistence.dao.user.UserDAO;
import de.nak.nakexammgmt.persistence.entity.user.User;
import de.nak.nakexammgmt.service.exception.AlreadyCreatedException;
import de.nak.nakexammgmt.service.exception.MissingIdException;
import de.nak.nakexammgmt.service.exception.NotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

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
        if (user.getId() != null) {
            throw new AlreadyCreatedException();
        }

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
        if (user.getId() == null) {
            throw new MissingIdException();
        }

        userDAO.save(user);
    }

    @Override
    public void updatePassword(User user, String password) throws NotFoundException {
        user.setPassword(passwordEncoder.encode(password));
    }

    @Override
    public void activate(User user) {
        if (user.getId() == null) {
            throw new MissingIdException();
        }
        user.setActivated(true);
        userDAO.save(user);
    }

    @Override
    public void deactivate(User user) {
        if (user.getId() == null) {
            throw new MissingIdException();
        }
        user.setActivated(false);
        user.setPassword(null);
        user.setCurrentUserSession(null);
        userDAO.save(user);
    }

    @Override
    public User get(Long id) throws NotFoundException {
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
