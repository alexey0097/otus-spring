package ru.diasoft.spring.homework.client;

import com.google.common.base.Strings;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.diasoft.spring.homework.config.AppConfigTest;
import ru.diasoft.spring.homework.parser.QuestionParserImpl;
import ru.diasoft.spring.homework.reader.QuestionResourceReader;
import ru.diasoft.spring.homework.service.QuestionService;
import ru.diasoft.spring.homework.service.QuestionServiceImpl;

class QuestionShellClientTest {

    private final AppConfigTest appConfigTest = new AppConfigTest();
    private final QuestionResourceReader questionResourceReader = new QuestionResourceReader(new QuestionParserImpl());
    private final QuestionService questionService = new QuestionServiceImpl(appConfigTest, questionResourceReader);
    private final QuestionShellClient questionShellClient = new QuestionShellClient(appConfigTest, questionService);

    @Test
    void startTest() {
        var text = questionShellClient.startTest("Zemtsov", "Alexey");
        Assertions.assertFalse(Strings.isNullOrEmpty(text));
    }

    @Test
    void endTest() {
        var text = questionShellClient.endTest("a,b,c,d,c,a,c");
        Assertions.assertFalse(Strings.isNullOrEmpty(text));
    }
}