package ua.vholovetskyi.bookshop.common.mail;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class EmailDto {
    private String to;
    private String from;
    private String subject;
    private String content;
}
