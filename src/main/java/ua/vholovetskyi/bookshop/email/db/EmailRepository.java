package ua.vholovetskyi.bookshop.email.db;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import ua.vholovetskyi.bookshop.email.model.EmailEntity;
import ua.vholovetskyi.bookshop.email.model.EmailStatus;

import java.util.List;

public interface EmailRepository extends ElasticsearchRepository<EmailEntity, String> {

    List<EmailEntity> findAllByStatus(EmailStatus status);
}
