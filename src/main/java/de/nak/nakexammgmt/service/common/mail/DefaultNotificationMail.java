/*
 * Copyright (c) 2014 Domenic Muskulus, Alexander Mersmann.
 * Licensed under the MIT License.
 */

package de.nak.nakexammgmt.service.common.mail;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

import java.util.HashSet;
import java.util.Set;

/**
 * Default implementation for the NotificationMail.
 *
 * @author Domenic Muskulus <domenic@muskulus.eu>
 */
public class DefaultNotificationMail implements NotificationMail {

    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultNotificationMail.class);

    private MessageSource messageSource;
    private MailSender mailSender;
    private String messageKey;
    private String subjectKey;
    private String from;

    private Set<SimpleMailMessage> mailMessages = new HashSet<>();

    @Override
    public void generate(String to, Object... args) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setSubject(messageSource.getMessage(subjectKey, null, null));
        mailMessage.setFrom(from);
        mailMessage.setTo(to);
        mailMessage.setText(messageSource.getMessage(messageKey, args, null));
        mailMessages.add(mailMessage);
    }

    @Override
    public void send() {
        if (mailMessages.isEmpty()) {
            throw new RuntimeException(); // FIXME: exception
        }

        try {
            LOGGER.info("Sending activation mails"); // TODO: remove
            mailSender.send(mailMessages.toArray(new SimpleMailMessage[mailMessages.size()]));
        } catch (MailException e) {
            LOGGER.warn("Mail delivery failed", e);
            throw e;
        } finally {
            mailMessages.clear();
        }
    }

    public void setMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    public void setMailSender(MailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void setMessageKey(String messageKey) {
        this.messageKey = messageKey;
    }

    public void setSubjectKey(String subjectKey) {
        this.subjectKey = subjectKey;
    }

    public void setFrom(String from) {
        this.from = from;
    }
}