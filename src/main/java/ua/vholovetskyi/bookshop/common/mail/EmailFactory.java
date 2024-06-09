package ua.vholovetskyi.bookshop.common.mail;

import ua.vholovetskyi.orderemail.messenger.model.Recipient;

public class EmailFactory {

    public static EmailDto createEmail(Recipient recipient, String sender) {
        return EmailDto.builder()
                .to(recipient.getTo())
                .from(sender)
                .subject(recipient.getSubject())
                .content(recipient.getContent())
                .build();
    }
}
