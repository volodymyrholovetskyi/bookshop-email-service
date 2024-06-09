package ua.vholovetskyi.bookshop.commons.mail;

public interface EmailSender {

    void sendEmail(EmailDetailsDto emailDetailsDto);
}
