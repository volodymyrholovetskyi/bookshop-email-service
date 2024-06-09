package ua.vholovetskyi.bookshop.email.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import ua.vholovetskyi.bookshop.commons.validator.EmailValidator;
import ua.vholovetskyi.bookshop.email.service.dto.EmailMessageDto;

import static ua.vholovetskyi.bookshop.email.mapper.EmailFactory.createNewEmail;

@Service
@Slf4j
@RequiredArgsConstructor
public class RabbitMqListener {

    private final EmailService emailService;
    private final EmailValidator validator;

    @RabbitListener(queues = "${app.email.listener.queue}", concurrency = "5")
    public void handleEmailMessage(final EmailMessageDto email) {
        if (!isValid(email)) {
            log.error("The email did not pass validation! {}", email);
            return;
        }
        emailService.saveAndSendEmail(createNewEmail(email));
    }

    private boolean isValid(final EmailMessageDto email) {
        return validator.validate(email);
    }
}
