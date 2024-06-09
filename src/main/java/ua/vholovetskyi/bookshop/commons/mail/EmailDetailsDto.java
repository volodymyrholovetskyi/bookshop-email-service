package ua.vholovetskyi.bookshop.commons.mail;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class EmailDetailsDto {
    private String to;
    private String from;
    private String subject;
    private String content;
}
