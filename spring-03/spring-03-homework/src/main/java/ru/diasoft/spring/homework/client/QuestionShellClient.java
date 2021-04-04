package ru.diasoft.spring.homework.client;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.shell.core.CommandMarker;
import org.springframework.shell.core.annotation.CliCommand;
import org.springframework.shell.core.annotation.CliOption;
import org.springframework.stereotype.Component;
import ru.diasoft.spring.homework.config.AppConfig;
import ru.diasoft.spring.homework.dto.QuestionDto;
import ru.diasoft.spring.homework.service.QuestionService;

import java.util.List;

@Component
@Log4j2
@RequiredArgsConstructor
public class QuestionShellClient implements CommandMarker {

    private static final String TEMPLATE_WELCOME = "Welcome, %s %s! \n";
    private static final String TEMPLATE_INFO = "We have few questions about history Great Britain. You have a purpose - answer this questions.\n";
    private static final String TEMPLATE_START = "Let's get started!\n";
    private static final String TEMPLATE_QUESTION = "    %s) %s\n";
    private static final String TEMPLATE_ANSWER = "        %s) %s\n";
    private static final String TEMPLATE_RESULTS = "You right answers: %s. Your results is %s";

    private final AppConfig appConfig;
    private final QuestionService questionService;

    @CliCommand(value = { "start-test", "st" })
    public String startTest(
            @CliOption(key = "firstName") String firstName,
            @CliOption(key = "lastName") String lastName
    ) {

        Integer countQuestions = appConfig.getCountQuestions();
        StringBuffer stringBuffer = new StringBuffer();

        stringBuffer.append(String.format(TEMPLATE_WELCOME, firstName, lastName));
        stringBuffer.append(TEMPLATE_INFO);
        stringBuffer.append(TEMPLATE_START);

        List<QuestionDto> questionList = questionService.findAll()
                .subList(0, countQuestions);

        questionList.forEach(question -> {
            stringBuffer.append(String.format(TEMPLATE_QUESTION, question.getNumber(), question.getText()));
            question.getAnswers().forEach(answer -> {
                stringBuffer.append(String.format(TEMPLATE_ANSWER, answer.getNumber(), answer.getText()));
            });
        });

        return stringBuffer.toString();
    }

    @CliCommand(value = { "end-test", "et" })
    public String endTest(@CliOption(key = "answers") String answers) {

        Integer countQuestions = appConfig.getCountQuestions();
        Integer minRightAnswers = appConfig.getMinRightAnswers();

        Table<Integer, String, Boolean> table = HashBasedTable.create();

        List<QuestionDto> questionList = questionService.findAll()
                .subList(0, countQuestions);

        questionList.forEach( question -> {
            question.getAnswers().forEach( answer -> {
                table.put(question.getNumber(), answer.getNumber(), answer.getIsCorrect());
            });
        });

        Integer countRightAnswer = 0;
        String[] variantsAnswer = answers.split(",");
        for (int i = 0; i < variantsAnswer.length; i++) {
            Integer numberQuestion = i + 1;
            String varAnswer = variantsAnswer[i];
            Boolean isCorrect = table.get(numberQuestion, varAnswer);
            if (isCorrect != null && isCorrect) countRightAnswer++;
        }

        String result = minRightAnswers <= countRightAnswer ? "GOOD" : "BAD";

        return String.format(TEMPLATE_RESULTS, countRightAnswer, result);
    }

}
