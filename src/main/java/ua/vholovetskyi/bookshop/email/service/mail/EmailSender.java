package ua.vholovetskyi.bookshop.commons.mail;

import jakarta.mail.MessagingException;

/**
 * @author Volodymyr Holovetskyi
 * @version 1.0
 * @since 2024-06-04
 */
public interface EmailSender {

    /**
     * Send email.
     *
     * @param emailDetailsDto data to send.
     * @throws MessagingException if email could not be sent.
     */
    void sendEmail(EmailDetailsDto emailDetailsDto) throws MessagingException;
}
