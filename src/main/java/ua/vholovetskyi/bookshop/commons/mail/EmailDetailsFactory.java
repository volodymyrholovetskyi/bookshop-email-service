package ua.vholovetskyi.bookshop.commons.mail;

import ua.vholovetskyi.bookshop.email.model.EmailEntity;

public class EmailDetailsFactory {

    public static EmailDetailsDto createEmailDetails(EmailEntity email, String fromSender) {
        return EmailDetailsDto.builder()
                .to(email.getTo())
                .from(fromSender)
                .subject(email.getSubject())
                .content(email.getContent())
                .build();
    }
}
