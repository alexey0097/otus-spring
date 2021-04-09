package ru.diasoft.spring.homework.client;

import com.google.common.base.Strings;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.diasoft.spring.homework.config.AppConfig;
import ru.diasoft.spring.homework.dto.AnswerDto;
import ru.diasoft.spring.homework.dto.QuestionDto;
import ru.diasoft.spring.homework.parser.QuestionParserImpl;
import ru.diasoft.spring.homework.reader.QuestionResourceReader;
import ru.diasoft.spring.homework.service.QuestionService;
import ru.diasoft.spring.homework.service.QuestionServiceImpl;

import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

@Log4j2
@ExtendWith({SpringExtension.class, MockitoExtension.class})
@ContextConfiguration(classes = {AppConfig.class, QuestionShellClient.class})
@DisplayName("Сервис для обработки вопросов")
class QuestionShellClientTest {

    @MockBean
    private QuestionService questionService;

    @Autowired
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

        when(questionService.findAll())
                .thenReturn(Arrays.asList(question));
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
        var questions = questionShellClient.startTest("Zemtsov", "Alexey");
        var text = questionShellClient.endTest("a,b,c,d,c,a,c");
        Assertions.assertFalse(Strings.isNullOrEmpty(text));
    }
}