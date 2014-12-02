/*
 * Copyright (c) 2014 Domenic Muskulus, Alexander Mersmann.
 * Licensed under the MIT License.
 */

package de.nak.nakexammgmt.service.user;

import de.nak.nakexammgmt.persistence.dao.user.PasswordResetDAO;
import de.nak.nakexammgmt.persistence.entity.user.PasswordReset;
import de.nak.nakexammgmt.persistence.entity.user.User;
import de.nak.nakexammgmt.service.authentication.AuthenticationService;
import de.nak.nakexammgmt.service.common.UrlProvider;
import de.nak.nakexammgmt.service.common.mail.NotificationMail;
import de.nak.nakexammgmt.service.exception.InvalidTokenException;
import de.nak.nakexammgmt.service.exception.NotFoundException;
import de.nak.nakexammgmt.util.DigestUtils;
import org.springframework.security.crypto.codec.Hex;
import org.springframework.security.crypto.keygen.KeyGenerators;

import java.time.Duration;
import java.time.LocalDateTime;

/**
 * Default implementation of the PasswordReset.
 *
 * @author Domenic Muskulus <domenic@muskulus.eu>
 */
public class DefaultPasswordResetService implements PasswordResetService {

    private static final Duration VALID_FOR = Duration.ofDays(1);

    private UserService userService;
    private PasswordResetDAO passwordResetDAO;
    private NotificationMail notificationMail;
    private AuthenticationService authenticationService;
    private UrlProvider urlProvider;

    @Override
    public void initResetProcess(String usernameOrEmail) throws NotFoundException {
        // Invalidate other password reset requests first
        User user = userService.get(usernameOrEmail);
        if (passwordResetDAO.has(user)) {
            passwordResetDAO.deleteByUser(user);
        }

        PasswordReset passwordReset = new PasswordReset();
        String token = generateToken();

        passwordReset.setUser(user);
        passwordReset.setToken(hash(token));
        passwordReset.setExpiresAt(LocalDateTime.now().plus(VALID_FOR));

        passwordResetDAO.save(passwordReset);

        sendNotification(user, token);
    }

    @Override
    public boolean validateResetRequest(String token) {
        PasswordReset passwordReset = passwordResetDAO.findByToken(hash(token));

        if (passwordReset == null) {
            return false;
        }

        if (passwordReset.getExpiresAt().isBefore(LocalDateTime.now())) {
            passwordResetDAO.delete(passwordReset);
            return false;
        }

        return true;
    }

    @Override
    public void finishPasswordReset(String token, String password) throws InvalidTokenException, NotFoundException {
        if (!validateResetRequest(token)) {
            throw new InvalidTokenException();
        }

        PasswordReset passwordReset = passwordResetDAO.findByToken(hash(token));

        userService.updatePassword(passwordReset.getUser(), password);

        passwordResetDAO.delete(passwordReset);
        authenticationService.revokeUserSessions(passwordReset.getUser());
    }

    @Override
    public void cancelResetProcess(User user) {
        passwordResetDAO.deleteByUser(user);
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
     * Sends a notification to the given user with a link for the PasswordReset.
     *
     * @param user  the user to send the mail to.
     * @param token the token for the PasswordReset.
     */
    private void sendNotification(User user, String token) {
        notificationMail.generate(
                user.getEmail(),
                user.getFullName(),
                urlProvider.urlForPasswordReset(token),
                urlProvider.urlForPasswordReset(null)
        );
        notificationMail.send();
    }

    public void setPasswordResetDAO(PasswordResetDAO passwordResetDAO) {
        this.passwordResetDAO = passwordResetDAO;
    }

    public void setNotificationMail(NotificationMail notificationMail) {
        this.notificationMail = notificationMail;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public void setAuthenticationService(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    public void setUrlProvider(UrlProvider urlProvider) {
        this.urlProvider = urlProvider;
    }
}
