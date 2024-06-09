package ua.vholovetskyi.bookshop.email.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailSendException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.vholovetskyi.bookshop.commons.mail.EmailSender;
import ua.vholovetskyi.bookshop.email.db.EmailRepository;
import ua.vholovetskyi.bookshop.email.model.EmailEntity;
import ua.vholovetskyi.bookshop.email.model.EmailStatus;

import java.util.List;

import static ua.vholovetskyi.bookshop.commons.mail.EmailDetailsFactory.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class EmailService {

    private static final String EMPTY_MSG = null;
    private static final String ERROR_MSG = "Class: %s. Message: %s";

    private final EmailSender emailSender;
    private final EmailRepository emailRepository;

    @Value("${app.email.sent.from}")
    private String fromSender;

    public List<EmailEntity> findAllByStatus(EmailStatus status) {
        return emailRepository.findAllByStatus(status);
    }

    @Transactional
    public void saveAndSendEmail(EmailEntity email) throws MailSendException {
        var savedEmail = emailRepository.save(email);
        sendEmail(savedEmail);
    }

    public void sendEmail(EmailEntity email) {
        try {
            log.info("Start sending email...");
            emailSender.sendEmail(createEmailDetails(email, fromSender));
            updateFields(email, EmailStatus.SENT, EMPTY_MSG);
            log.info("Email sent successfully!");
        } catch (MailSendException err) {
            updateFields(email, EmailStatus.FAILED, formatErrorMessage(err.getClass().getName(), err.getMessage()));
            log.error("Error sending email. {}", err.getMessage());
        } catch (Exception err) {
            log.error("Error in sendMessage() method. {}", err.getMessage());
        }
    }

    private void updateFields(EmailEntity email, EmailStatus status, String errorMsg) {
        email.updateFields(status, errorMsg);
        emailRepository.save(email);
    }

    private String formatErrorMessage(String clazzName, String message) {
        return ERROR_MSG.formatted(clazzName, message);
    }
}
