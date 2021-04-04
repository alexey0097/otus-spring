package ru.diasoft.spring.homework.reader;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import ru.diasoft.spring.homework.config.AppConfigTest;
import ru.diasoft.spring.homework.parser.QuestionParser;

import static org.junit.jupiter.api.Assertions.assertFalse;

@Log4j2
class QuestionResourceReaderTest {

    private final AppConfigTest appConfigTest = new AppConfigTest();
    private final QuestionResourceReader questionResourceReader = new QuestionResourceReader(new QuestionParser());

    @Test
    void read() {
        var listQuestions = questionResourceReader.read(appConfigTest.getPathToQuestions());
        assertFalse(listQuestions.isEmpty());
    }
}