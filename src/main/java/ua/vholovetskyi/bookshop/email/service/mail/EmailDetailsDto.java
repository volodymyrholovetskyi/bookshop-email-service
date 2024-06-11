package ua.vholovetskyi.bookshop.commons.mail;

import lombok.Builder;
import lombok.Getter;

import java.util.Map;

/**
 * @author Volodymyr Holovetskyi
 * @version 1.0
 * @since 2024-06-04
 */
@Builder
@Getter
public class EmailDetailsDto {
    private String to;
    private String from;
    private String subject;
    private Map<String, Object> body;
    private String templateName;
}
