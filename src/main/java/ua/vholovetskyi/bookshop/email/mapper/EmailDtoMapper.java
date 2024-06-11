package ua.vholovetskyi.bookshop.email.mapper;

import ua.vholovetskyi.bookshop.email.model.EmailEntity;
import ua.vholovetskyi.bookshop.email.service.mail.EmailDetailsDto;

/**
 * @author Volodymyr Holovetskyi
 * @version 1.0
 * @since 2024-06-04
 */
public class EmailDtoMapper {

    public static EmailDetailsDto mapToEmailDetailsDto(EmailEntity email) {
        return EmailDetailsDto.builder()
                .to(email.getTo())
                .subject(email.getSubject())
                .body(email.getBody())
                .templateName(email.getTemplateName())
                .build();
    }
}
