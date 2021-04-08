package ru.diasoft.spring.homework.parser;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.diasoft.spring.homework.dto.AnswerDto;
import ru.diasoft.spring.homework.dto.QuestionDto;
import ru.diasoft.spring.homework.service.QuestionServiceImpl;

import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@Log4j2
@DisplayName("Парсер для строк")
class QuestionParserTest {

    private QuestionParser questionParser;

    /**
     * По другому не проверить. Для проверки парсинга нужны конкретные  строки.
     */
    private final static String QUESTION_LINE = "1;Which two dynasties fought for the throne in the War of the Scarlet and White Rose?";
    private final static String ANSWERS_LINE = "a&Lancaster&York&true|b&Plantagenets&Lancaster&false|c&Tudors&Plantagenets&false";
    private final static String ANSWER_LINE = "a&Lancaster&York&true";

    @BeforeEach
    void init() {
        questionParser = new QuestionParserImpl();
    }

    @DisplayName("парсит вопрос")
    @Test
    void parseQuestion() {
        var question = questionParser.parseQuestion(QUESTION_LINE);
        Assertions.assertTrue(question.isPresent());
    }

    @DisplayName("парсит ответы")
    @Test
    void parseAnswers() {
        var answers = questionParser.parseAnswers(ANSWERS_LINE);
        Assertions.assertFalse(answers.isEmpty());
    }

    @DisplayName("парсит ответ")
    @Test
    void parseAnswer() {
        var answer = questionParser.parseAnswer(ANSWER_LINE);
        Assertions.assertTrue(answer.isPresent());
    }
}