package ua.vholovetskyi.bookshop.email.service;

import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.MailException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.vholovetskyi.bookshop.email.service.mail.EmailSender;
import ua.vholovetskyi.bookshop.email.db.EmailRepository;
import ua.vholovetskyi.bookshop.email.model.EmailEntity;
import ua.vholovetskyi.bookshop.email.model.EmailStatus;

import java.util.List;

import static ua.vholovetskyi.bookshop.email.mapper.EmailDtoMapper.mapToEmailDetailsDto;
import static ua.vholovetskyi.bookshop.commons.utils.MessageFormatters.EMPTY_MSG;
import static ua.vholovetskyi.bookshop.commons.utils.MessageFormatters.formatMessage;

/**
 * @author Volodymyr Holovetskyi
 * @version 1.0
 * @since 2024-06-03
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class EmailService {

    private final EmailSender emailSender;
    private final EmailRepository emailRepository;

    /**
     * Find all emails by status.
     *
     * @param status find by criteria.
     * @return List<EmailEntity> or an emptyList if nothing is found.
     */
    public List<EmailEntity> findAllByStatus(EmailStatus status) {
        return emailRepository.findAllByStatus(status);
    }

    /**
     * Save message in DB.
     *
     * @param email data to save in DB.
     */
    @Transactional
    public void saveEmail(EmailEntity email) {
        var savedEmail = emailRepository.save(email);
        sendEmail(savedEmail);
    }

    /**
     * Send email and changes status.
     * If the mail is sent successfully, the status updates to SENT
     * otherwise status updates to FAILED.
     *
     * @param email data to send.
     */
    public void sendEmail(EmailEntity email) {
        try {
            log.info("Start sending email...");
            emailSender.sendEmail(mapToEmailDetailsDto(email));
            updateFields(email, EmailStatus.SENT, EMPTY_MSG);
            log.info("Email sent successfully!");
        } catch (MessagingException | MailException err) {
            updateFields(email, EmailStatus.FAILED, formatMessage(err));
            log.error("Error sending email. {}", err.getMessage());
        } catch (Exception err) {
            log.error("Error in sendEmail() method. {}", err.getMessage());
        }
    }

    private void updateFields(EmailEntity email, EmailStatus status, String errorMsg) {
        email.updateFields(status, errorMsg);
        emailRepository.save(email);
    }
}
