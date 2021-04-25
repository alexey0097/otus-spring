package ru.diasoft.spring.homework.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.util.Locale;

@Configuration
public class QuestionProperties {

    @Value("${config.questions.path}")
    @Getter
    private String pathToQuestions;

    @Value("${config.questions.count}")
    @Getter
    private Integer countQuestions;

    @Value("${config.questions.min}")
    @Getter
    private Integer minRightAnswers;

    @Value("${config.questions.locale}")
    private String locale;

    public Locale getLocale() {
        return new Locale(locale);
    }

}
