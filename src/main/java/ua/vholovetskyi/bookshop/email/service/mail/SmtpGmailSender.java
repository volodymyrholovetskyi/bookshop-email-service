package ua.vholovetskyi.bookshop.email.service.mail;

import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

/**
 * @author Volodymyr Holovetskyi
 * @version 1.0
 * @since 2024-06-04
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class SmtpGmailSender extends BaseEmailSender {

    private final JavaMailSender mailSender;

    @Value("${app.email.sent.from}")
    private String fromSender;

    @Override
    public void sendEmail(EmailDetailsDto email) throws MessagingException {
        var message = mailSender.createMimeMessage();
        var helper = new MimeMessageHelper(message, true, "UTF-8");
        helper.setTo(email.getTo());
        helper.setFrom(fromSender);
        helper.setReplyTo(fromSender);
        helper.setSubject(email.getSubject());
        helper.setText(generateEmailBody(email), true);
        mailSender.send(message);
    }
}
