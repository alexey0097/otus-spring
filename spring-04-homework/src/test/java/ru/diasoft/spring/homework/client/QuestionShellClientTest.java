package ru.diasoft.spring.homework.client;

import com.google.common.base.Strings;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.diasoft.spring.homework.config.AppConfig;
import ru.diasoft.spring.homework.dto.AnswerDto;
import ru.diasoft.spring.homework.dto.QuestionDto;
import ru.diasoft.spring.homework.service.QuestionService;

import java.util.Arrays;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

@Log4j2
@ExtendWith({MockitoExtension.class})
@DisplayName("Сервис для обработки вопросов")
class QuestionShellClientTest {

    @Mock
    private AppConfig appConfig;

    @Mock
    private QuestionService questionService;

    @InjectMocks
    private QuestionShellClient questionShellClient;

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

        given(questionService.findAll())
                .willReturn(Arrays.asList(question));

        when(appConfig.getCountQuestions())
                .thenReturn(12);
    }

    @DisplayName("запускает тестирование")
    @Test
    void startTest() {
        var text = questionShellClient.startTest("Zemtsov", "Alexey");
        Assertions.assertFalse(Strings.isNullOrEmpty(text));
    }

    @DisplayName("завершает тестирование")
    @Test
    void endTest() {
        when(appConfig.getCountQuestions())
                .thenReturn(5);

        when(appConfig.getMinRightAnswers())
                .thenReturn(3);

        var questions = questionShellClient.startTest("Zemtsov", "Alexey");
        var text = questionShellClient.endTest("a,b,c,d,c,a,c");
        Assertions.assertFalse(Strings.isNullOrEmpty(text));
    }
}