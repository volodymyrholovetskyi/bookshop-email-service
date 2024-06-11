package ua.vholovetskyi.bookshop.email.mapper;

import ua.vholovetskyi.bookshop.email.model.EmailEntity;
import ua.vholovetskyi.bookshop.email.model.EmailStatus;
import ua.vholovetskyi.bookshop.email.service.dto.EmailMessageDto;

import java.time.Instant;

/**
 * @author Volodymyr Holovetskyi
 * @version 1.0
 * @since 2024-06-04
 */
public class EmailFactory {

    public static EmailEntity createNewEmail(EmailMessageDto email) {
        return EmailEntity.builder()
                .to(email.getTo())
                .subject(email.getSubject())
                .body(email.getBody())
                .templateName(email.getTemplateName())
                .status(EmailStatus.PENDING)
                .createdAt(Instant.now())
                .updatedAt(Instant.now())
                .build();
    }
}
