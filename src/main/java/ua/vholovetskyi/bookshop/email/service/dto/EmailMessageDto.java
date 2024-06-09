package ua.vholovetskyi.bookshop.email.service.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EmailMessageDto {

    @Email(message = "{email.invalidFormat}")
    @NotBlank(message = "{email.isBlank}")
    private String to;

    @NotBlank(message = "{email.isBlank}")
    private String subject;

    @NotBlank(message = "{email.isBlank}")
    private String content;
}
