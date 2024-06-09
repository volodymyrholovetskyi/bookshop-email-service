package ua.vholovetskyi.bookshop.commons.validator;

import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ua.vholovetskyi.bookshop.email.service.dto.EmailMessageDto;

@Component
@RequiredArgsConstructor
public class EmailValidator {

    private final Validator validator;

    /**
     * Validate date from the user ((incorrect mail format, empty subject or content)
     *
     * @param email object for validation
     * */
    public boolean validate(final EmailMessageDto email) {
        return validator.validate(email).isEmpty();
    }
}
