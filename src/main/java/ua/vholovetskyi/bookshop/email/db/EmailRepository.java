package ua.vholovetskyi.bookshop.email.db;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import ua.vholovetskyi.bookshop.email.model.EmailEntity;
import ua.vholovetskyi.bookshop.email.model.EmailStatus;

import java.util.List;

/**
 * @author Volodymyr Holovetskyi
 * @version 1.0
 * @since 2024-06-04
 */
public interface EmailRepository extends ElasticsearchRepository<EmailEntity, String> {

    List<EmailEntity> findAllByStatus(EmailStatus status);
}
