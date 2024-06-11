package ua.vholovetskyi.bookshop.email.service;

import jakarta.mail.MessagingException;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.junit.jupiter.Testcontainers;
import ua.vholovetskyi.bookshop.BaseElasticsearchIT;
import ua.vholovetskyi.bookshop.email.db.EmailRepository;
import ua.vholovetskyi.bookshop.email.model.EmailEntity;
import ua.vholovetskyi.bookshop.email.model.EmailStatus;
import ua.vholovetskyi.bookshop.email.service.mail.EmailDetailsDto;
import ua.vholovetskyi.bookshop.email.service.mail.EmailSender;

import java.time.Instant;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class EmailServiceIT extends BaseElasticsearchIT {

    @Autowired
    private EmailService emailService;

    @Autowired
    private EmailRepository emailRepository;

    @MockBean
    private EmailSender emailSender;

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    @BeforeAll
    static void setUp() {
        elasticContainer.start();
    }

    @BeforeEach
    void setUpContainerRunning() {
        assertTrue(elasticContainer.isRunning());
        recreateIndex();
    }

    @Test
    void shouldCreateEmailAndUpdateStatusAsSent() throws MessagingException {
        //given
        var email = givenEmail();

        //when
        emailService.saveEmail(email);
        var emails = emailService.findAllByStatus(EmailStatus.SENT);

        //then
        assertEquals(emails.size(), 1);
        assertEquals(emails.get(0).getStatus(), EmailStatus.SENT);
        assertNull(emails.get(0).getErrorMessage());
        verify(emailSender, times(1)).sendEmail(any(EmailDetailsDto.class));
    }

    @Test
    void shouldCreateEmailAndUpdateStatusAsFailed() throws MessagingException {
        //given
        var email = givenEmail();

        //when
        doThrow(MessagingException.class).when(emailSender).sendEmail(any(EmailDetailsDto.class));
        emailService.saveEmail(email);
        var emails = emailService.findAllByStatus(EmailStatus.FAILED);

        //then
        assertEquals(emails.size(), 1);
        assertEquals(emails.get(0).getStatus(), EmailStatus.FAILED);
        assertNotNull(emails.get(0).getErrorMessage());
        verify(emailSender, times(1)).sendEmail(any(EmailDetailsDto.class));
    }

    private EmailEntity givenEmail() {
        return EmailEntity.builder()
                .to("customer@test.com")
                .subject("Test Subject")
                .body(Map.of("fullName", "First Customer"))
                .status(EmailStatus.PENDING)
                .templateName("customer-template")
                .createdAt(Instant.now())
                .updatedAt(Instant.now())
                .build();
    }

    private void recreateIndex() {
        if (elasticsearchTemplate.indexOps(EmailEntity.class).exists()) {
            elasticsearchTemplate.indexOps(EmailEntity.class).delete();
            elasticsearchTemplate.indexOps(EmailEntity.class).create();
        }
    }

    @AfterAll
    static void tearDown() {
        elasticContainer.stop();
    }
}