package ru.diasoft.spring.homework.config;

import lombok.Getter;


public class AppConfigTest extends AppConfig{

    @Getter
    private final String pathToQuestions = "online-test-ru.csv";

    @Getter
    private final Integer countQuestions = 12;

    @Getter
    private final Integer minRightAnswers = 3;
}