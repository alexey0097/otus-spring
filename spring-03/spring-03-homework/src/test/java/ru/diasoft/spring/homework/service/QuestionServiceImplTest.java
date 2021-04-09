package ru.diasoft.spring.homework.service;

import org.junit.jupiter.api.Test;
import ru.diasoft.spring.homework.config.AppConfigTest;
import ru.diasoft.spring.homework.parser.QuestionParserImpl;
import ru.diasoft.spring.homework.reader.QuestionResourceReader;

import static org.junit.jupiter.api.Assertions.*;

class QuestionServiceImplTest {

    private final AppConfigTest appConfigTest = new AppConfigTest();
    private final QuestionResourceReader questionResourceReader = new QuestionResourceReader(new QuestionParserImpl());
    private final QuestionService questionService = new QuestionServiceImpl(appConfigTest, questionResourceReader);

    @Test
    void findAll() {
        var listQuestions = questionService.findAll();
        assertFalse(listQuestions.isEmpty());
    }
}