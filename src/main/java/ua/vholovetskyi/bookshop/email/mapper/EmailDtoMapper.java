package ua.vholovetskyi.bookshop.email.service.mail;

import ua.vholovetskyi.bookshop.email.model.EmailEntity;

/**
 * @author Volodymyr Holovetskyi
 * @version 1.0
 * @since 2024-06-04
 */
public class EmailDetailsFactory {

    public static EmailDetailsDto createEmailDetails(EmailEntity email) {
        return EmailDetailsDto.builder()
                .to(email.getTo())
                .subject(email.getSubject())
                .body(email.getBody())
                .templateName(email.getTemplateName())
                .build();
    }
}
