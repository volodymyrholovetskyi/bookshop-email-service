package ua.vholovetskyi.bookshop.email.mapper;

import ua.vholovetskyi.bookshop.email.model.EmailEntity;
import ua.vholovetskyi.bookshop.email.model.EmailStatus;
import ua.vholovetskyi.bookshop.email.service.dto.EmailMessageDto;

import java.time.Instant;

public class EmailFactory {

    public static EmailEntity createNewEmail(EmailMessageDto message) {
        return EmailEntity.builder()
                .to(message.getTo())
                .subject(message.getSubject())
                .content(message.getContent())
                .status(EmailStatus.PENDING)
                .createdAt(Instant.now())
                .updatedAt(Instant.now())
                .build();
    }
}
