package ru.diasoft.spring.homework.service;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.diasoft.spring.homework.dto.QuestionDto;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;

@Log4j2
class QuestionServiceTest {

    private static ClassPathXmlApplicationContext CONTEXT;

    @BeforeAll
    static void beforeAll() {
        CONTEXT = new ClassPathXmlApplicationContext("/spring-context.xml");
    }

    @DisplayName("Test QuestionService.findAll()")
    @Test
    void findAll() {
        QuestionService questionService = CONTEXT.getBean(QuestionService.class);
        List<QuestionDto> questions = questionService.findAll();
        questions.forEach(log::info);
        assertFalse(questions.isEmpty());
    }

    @AfterAll
    static void afterAll() {
        CONTEXT.close();
    }

}