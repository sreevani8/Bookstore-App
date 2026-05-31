package com.mulit.bookstore.webapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.jdbc.autoconfigure.DataSourceAutoConfiguration;

@SpringBootApplication(
        exclude = {DataSourceAutoConfiguration.class}
)
@EnableConfigurationProperties(ApplicationProperties.class)
public class BookstoreWebappApplication {

    public static void main(String[] args) {
        SpringApplication.run(BookstoreWebappApplication.class, args);
    }
}
