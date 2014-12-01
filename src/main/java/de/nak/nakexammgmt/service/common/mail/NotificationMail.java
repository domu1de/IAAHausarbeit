/*
 * Copyright (c) 2014 Domenic Muskulus, Alexander Mersmann.
 * Licensed under the MIT License.
 */

package de.nak.nakexammgmt.service.common.mail;

/**
 * Contract for a Notification Mail in the system. The contract allows sending multiple mails at once.
 *
 * @author Domenic Muskulus <domenic@muskulus.eu>
 */
public interface NotificationMail {

    /**
     * Generates a new NotificationMail for the given recipient with the given arguments.
     * It is possible to generate multiple NotificationMails.
     *
     * @param to   the recipient of the generated mail.
     * @param args arguments for the generated mail.
     */
    void generate(String to, Object... args);

    /**
     * Sends all previously generated NotificationMails.
     */
    void send();

}
