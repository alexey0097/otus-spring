package ru.diasoft.spring.homework.service;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.diasoft.spring.homework.config.AppConfig;
import ru.diasoft.spring.homework.dto.AnswerDto;
import ru.diasoft.spring.homework.dto.QuestionDto;
import ru.diasoft.spring.homework.reader.QuestionResourceReader;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@Log4j2
@ExtendWith({SpringExtension.class, MockitoExtension.class})
@ContextConfiguration(classes = AppConfig.class)
@DisplayName("Сервис для поиска вопросов")
class QuestionServiceImplTest {

    @Autowired
    private AppConfig appConfig;

    @Mock
    private QuestionResourceReader questionResourceReader;

    private QuestionService questionService;

    @BeforeEach
    void init() {
        QuestionDto question = new QuestionDto();
        question.setNumber(1);
        question.setText("Заглушечный вопрос №1");

        AnswerDto answer1 = new AnswerDto();
        answer1.setNumber("a");
        answer1.setText("Заглушечный ответ 1");
        answer1.setIsCorrect(false);

        AnswerDto answer2 = new AnswerDto();
        answer2.setNumber("b");
        answer2.setText("Заглушечный ответ 2");
        answer2.setIsCorrect(true);

        question.setAnswers(Arrays.asList(answer1, answer2));

        given(questionResourceReader.read(any()))
                .willReturn(Arrays.asList(question));

        questionService = new QuestionServiceImpl(appConfig, questionResourceReader);
    }

    @DisplayName("получает список вопросов")
    @Test
    void findAll() {
        var listQuestions = questionService.findAll();
        assertFalse(listQuestions.isEmpty());
    }
}