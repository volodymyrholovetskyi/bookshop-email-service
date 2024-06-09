package ua.vholovetskyi.bookshop;

import org.springframework.boot.SpringApplication;

public class TestBookshopEmailServiceApplication {

    public static void main(String[] args) {
        SpringApplication.from(BookshopEmailServiceApplication::main).with(TestcontainersConfiguration.class).run(args);
    }

}
