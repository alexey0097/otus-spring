package ru.diasoft.app.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LocatorConfig {

    @Value("${book-service.url}")
    @Getter
    private String bookServiceUrl;

}
