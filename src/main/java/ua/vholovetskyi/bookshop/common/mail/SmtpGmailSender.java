package ua.vholovetskyi.bookshop.common.mail;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class SmtpGmailSender implements EmailSender {

    private final JavaMailSender mailSender;

    @Override
    public void sendEmail(EmailDto email) {
            var message = new SimpleMailMessage();
            message.setTo(email.getTo());
            message.setFrom(email.getFrom());
            message.setReplyTo(email.getFrom());
            message.setSubject(email.getSubject());
            message.setText(email.getContent());
            mailSender.send(message);
    }
}
