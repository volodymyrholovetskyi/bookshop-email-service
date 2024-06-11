package ua.vholovetskyi.bookshop.email.service.mail;

import org.springframework.core.io.ClassPathResource;
import org.springframework.util.StreamUtils;
import ua.vholovetskyi.bookshop.commons.exception.TemplateException;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Objects;

/**
 * @author Volodymyr Holovetskyi
 * @version 1.0
 * @since 2024-06-04
 */
public abstract class BaseEmailSender implements EmailSender {

    private static final String FORMAT_PATH = "templates/emails/%s.html";

    /**
     * Generate body.
     *
     * @param email data to create body.
     * @return html string.
     */
    protected String generateEmailBody(EmailDetailsDto email) {
        if (Objects.isNull(email.getBody())) {
            throw new IllegalArgumentException("Email body is null");
        }
        var templateName = email.getTemplateName();

        var body = loadEmailTemplate(templateName);
        for (Map.Entry<String, Object> entry : email.getBody().entrySet()) {
            body = body.replace("{{" + entry.getKey() + "}}", Objects.toString(entry.getValue()));
        }
        return body;
    }

    /**
     * Load template.
     *
     * @param templateName template name.
     * @return template.
     */
    private String loadEmailTemplate(String templateName) {
        var resource = new ClassPathResource(FORMAT_PATH.formatted(templateName));
        try {
            return StreamUtils.copyToString(resource.getInputStream(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new TemplateException(templateName, e);
        }
    }
}
