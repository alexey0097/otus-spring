package ru.diasoft.spring.homework.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:application.properties")
public class AppConfig {

    @Value("${path.to.questions}")
    @Getter
    private String pathToQuestions;

    @Value("${count.questions}")
    @Getter
    private Integer countQuestions;

    @Value("${min.right.answers}")
    @Getter
    private Integer minRightAnswers;

}
