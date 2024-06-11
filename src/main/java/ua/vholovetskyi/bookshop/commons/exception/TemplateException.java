package ua.vholovetskyi.bookshop.commons.exception;

/**
 * @author Volodymyr Holovetskyi
 * @version 1.0
 * @since 2024-06-04
 */
public class EmailTemplateException extends RuntimeException {

    public EmailTemplateException(String templateName, Throwable e) {
        super("Error loading email template: " + templateName, e);
    }
}
