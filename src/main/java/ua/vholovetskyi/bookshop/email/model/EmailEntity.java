package ua.vholovetskyi.bookshop.email.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.time.Instant;

@Data
@Builder
@Document(indexName = "email")
public class EmailEntity {

    @Id
    private String id;

    @Field(type = FieldType.Text)
    private String to;

    @Field(type = FieldType.Text)
    private String subject;

    @Field(type = FieldType.Text)
    private String content;

    @Field(type = FieldType.Keyword)
    private EmailStatus status;

    @Field(type = FieldType.Text)
    private String errorMessage;

    @Field(type = FieldType.Integer)
    private int numberOfAttempts;

    @Field(type = FieldType.Date)
    private Instant createdAt;

    @Field(type = FieldType.Date)
    private Instant updatedAt;

    public void updateFields(EmailStatus newStatus, String errorMessage) {
        if (newStatus == null || status == newStatus) {
            return;
        }
        this.status = newStatus;
        this.errorMessage = errorMessage;
        ++numberOfAttempts;
        updatedAt = Instant.now();
    }
}
