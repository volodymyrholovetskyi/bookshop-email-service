package ua.vholovetskyi.bookshop.email.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import ua.vholovetskyi.bookshop.email.service.dto.EmailMessageDto;

import static ua.vholovetskyi.bookshop.email.mapper.EmailFactory.createNewEmail;

/**
 * @author Volodymyr Holovetskyi
 * @version 1.0
 * @since 2024-06-02
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class RabbitMqListener {

    private final EmailService emailService;

    /**
     * Receives data from the email-queue.
     *
     * @param email received data.
     */
    @RabbitListener(queues = "${rabbitmq.queue.email}", concurrency = "5")
    public void handleEmailMessage(final EmailMessageDto email) {
        emailService.saveEmail(createNewEmail(email));
    }
}
