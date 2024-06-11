package ua.vholovetskyi.bookshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.elasticsearch.config.EnableElasticsearchAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class BookshopEmailServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BookshopEmailServiceApplication.class, args);
    }

}
