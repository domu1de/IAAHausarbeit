/*
 * Copyright (c) 2014 Domenic Muskulus, Alexander Mersmann.
 * Licensed under the MIT License.
 */

package de.nak.exammgmt.service.user;

import de.nak.exammgmt.persistence.dao.user.ActivationDAO;
import de.nak.exammgmt.persistence.entity.user.Activation;
import de.nak.exammgmt.persistence.entity.user.User;
import de.nak.exammgmt.service.common.UrlProvider;
import de.nak.exammgmt.service.common.mail.NotificationMail;
import de.nak.exammgmt.service.exception.AlreadyActivatedException;
import de.nak.exammgmt.service.exception.InvalidTokenException;
import de.nak.exammgmt.service.exception.NotFoundException;
import de.nak.exammgmt.util.DigestUtils;
import org.springframework.security.crypto.codec.Hex;
import org.springframework.security.crypto.keygen.KeyGenerators;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Default implementation of the ActivationService.
 *
 * @author Domenic Muskulus <domenic@muskulus.eu>
 */
public class DefaultActivationService implements ActivationService {

    private static final Duration VALID_FOR = Duration.ofDays(7);

    private UserService userService;
    private ActivationDAO activationDAO;
    private NotificationMail notificationMail;
    private UrlProvider urlProvider;

    @Override
    public void create(User user) throws AlreadyActivatedException {
        Objects.requireNonNull(user);

        // Invalidate other activations first
        if (activationDAO.has(user)) {
            activationDAO.deleteByUser(user);
        }

        if (user.isActivated()) {
            throw new AlreadyActivatedException();
        }

        Activation activation = new Activation();
        String token = generateToken();

        activation.setUser(user);
        activation.setToken(hash(token));
        activation.setExpiresAt(LocalDateTime.now().plus(VALID_FOR));

        activationDAO.save(activation);

        sendNotification(user, token);
    }

    @Override
    public boolean validate(String token) {
        Objects.requireNonNull(token);

        Activation activation = activationDAO.findByToken(hash(token));

        if (activation == null) {
            return false;
        }

        if (activation.getUser().isActivated() || activation.getExpiresAt().isBefore(LocalDateTime.now())) {
            activationDAO.delete(activation);
            return false;
        }

        return true;
    }

    @Override
    public void finish(String token, String password) throws InvalidTokenException, NotFoundException {
        Objects.requireNonNull(token);
        Objects.requireNonNull(password);

        if (!validate(token)) {
            throw new InvalidTokenException();
        }

        Activation activation = activationDAO.findByToken(hash(token));

        userService.updatePassword(activation.getUser(), password);

        userService.activate(activation.getUser());
        activationDAO.delete(activation);
    }

    @Override
    public void cancel(User user) {
        Objects.requireNonNull(user);
        Objects.requireNonNull(user.getId());

        activationDAO.deleteByUser(user);
    }

    /**
     * Generates a cryptographically secure random token.
     *
     * @return the hex encoded token
     */
    private String generateToken() {
        return new String(Hex.encode(KeyGenerators.secureRandom(32).generateKey()));
    }

    /**
     * Calculates the hash of the given string.
     *
     * @param data the data to hash
     * @return hash hex encoded
     */
    private String hash(String data) {
        return DigestUtils.sha256Hex(data);
    }

    /**
     * Sends a notification with the activation link to the user.
     *
     * @param user  the user to activate.
     * @param token the token of the activation.
     */
    private void sendNotification(User user, String token) {
        notificationMail.generate(user.getEmail(), user.getFullName(), urlProvider.urlForActivation(token));
        notificationMail.send();
    }

    public void setActivationDAO(ActivationDAO activationDAO) {
        this.activationDAO = activationDAO;
    }

    public void setNotificationMail(NotificationMail notificationMail) {
        this.notificationMail = notificationMail;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public void setUrlProvider(UrlProvider urlProvider) {
        this.urlProvider = urlProvider;
    }
}
