package ru.diasoft.spring.homework.parser;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

@Log4j2
class QuestionParserTest {

    private final QuestionParserImpl questionParserImpl = new QuestionParserImpl();

    private final static String QUESTION_LINE = "1;Which two dynasties fought for the throne in the War of the Scarlet and White Rose?";
    private final static String ANSWERS_LINE = "a&Lancaster&York&true|b&Plantagenets&Lancaster&false|c&Tudors&Plantagenets&false";
    private final static String ANSWER_LINE = "a&Lancaster&York&true";

    @Test
    void parseQuestion() {
        var question = questionParserImpl.parseQuestion(QUESTION_LINE);
        Assertions.assertTrue(question.isPresent());
    }

    @Test
    void parseAnswers() {
        var answers = questionParserImpl.parseAnswers(ANSWERS_LINE);
        Assertions.assertFalse(answers.isEmpty());
    }

    @Test
    void parseAnswer() {
        var answer = questionParserImpl.parseAnswer(ANSWER_LINE);
        Assertions.assertTrue(answer.isPresent());
    }
}