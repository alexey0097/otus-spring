package ru.diasoft.spring.homework.client;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.diasoft.spring.homework.config.AppConfig;
import ru.diasoft.spring.homework.dto.QuestionDto;
import ru.diasoft.spring.homework.service.QuestionService;

import java.util.List;

@ShellComponent
@Log4j2
@RequiredArgsConstructor
public class QuestionShellClient {

    private static final String TEMPLATE_WELCOME = "Welcome, %s %s! \n";
    private static final String TEMPLATE_INFO = "We have few questions about history Great Britain. You have a purpose - answer this questions.\n";
    private static final String TEMPLATE_START = "Let's get started!\n";
    private static final String TEMPLATE_QUESTION = "    %s) %s\n";
    private static final String TEMPLATE_ANSWER = "        %s) %s\n";
    private static final String TEMPLATE_RESULTS = "You right answers: %s. Your results is %s";

    private final AppConfig appConfig;
    private final QuestionService questionService;

    private boolean questionIsSed = false;

    @ShellMethod(key = "start-test", value = "Method start test")
    public String startTest(
            @ShellOption String firstName,
            @ShellOption String lastName
    ) {

        Integer countQuestions = appConfig.getCountQuestions();
        StringBuffer stringBuffer = new StringBuffer();

        stringBuffer.append(String.format(TEMPLATE_WELCOME, firstName, lastName));
        stringBuffer.append(TEMPLATE_INFO);
        stringBuffer.append(TEMPLATE_START);

        List<QuestionDto> questionList = questionService.findAll();

        if (countQuestions > questionList.size()) {
            countQuestions = questionList.size();
        }
        questionList = questionList.subList(0, countQuestions);

        questionList.forEach(question -> {
            stringBuffer.append(String.format(TEMPLATE_QUESTION, question.getNumber(), question.getText()));
            question.getAnswers().forEach(answer -> {
                stringBuffer.append(String.format(TEMPLATE_ANSWER, answer.getNumber(), answer.getText()));
            });
        });

        questionIsSed = true;

        return stringBuffer.toString();
    }

    @ShellMethod(key = "end-test", value = "Method end test")
    public String endTest(@ShellOption("answers") String answers) {
        if (!questionIsSed) return "please exec command start-test";

        Integer countQuestions = appConfig.getCountQuestions();
        Integer minRightAnswers = appConfig.getMinRightAnswers();

        Table<Integer, String, Boolean> table = HashBasedTable.create();

        List<QuestionDto> questionList = questionService.findAll();

        if (countQuestions > questionList.size()) {
            countQuestions = questionList.size();
        }
        questionList = questionList.subList(0, countQuestions);

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

        String result;

        if (minRightAnswers <= countRightAnswer) {
            result = "GOOD";
        } else {
            result = "BAD";
        }
        questionIsSed = false;

        return String.format(TEMPLATE_RESULTS, countRightAnswer, result);
    }

}
