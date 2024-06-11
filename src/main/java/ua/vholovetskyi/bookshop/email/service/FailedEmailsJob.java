package ua.vholovetskyi.bookshop.email.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ua.vholovetskyi.bookshop.email.model.EmailStatus;

/**
 * @author Volodymyr Holovetskyi
 * @version 1.0
 * @since 2024-06-02
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class FailedEmailsJob {

    private final EmailService messageService;

    /**
     * Task scheduler.
     * Search and tries to resend a message with a FAILED status.
     */
    @Transactional
    @Scheduled(cron = "${app.email.failed-cron}")
    public void run() {
        try {
            log.info("Starting Scheduled Task...");
            var emails = messageService.findAllByStatus(EmailStatus.FAILED);
            log.info("Found failed messages to resend: {}", emails.size());
            emails.forEach(messageService::sendEmail);
            log.info("Scheduled Task completed successfully!");
        } catch (Exception e) {
            log.error("Error executing scheduled task: {}", e.getMessage());
        }
    }
}
