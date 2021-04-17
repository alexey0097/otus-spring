package ru.diasoft.spring.homework.client;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;
import org.springframework.shell.standard.ShellOption;
import ru.diasoft.spring.homework.config.AppConfig;
import ru.diasoft.spring.homework.config.QuestionProperties;
import ru.diasoft.spring.homework.dto.QuestionDto;
import ru.diasoft.spring.homework.service.MessageSourceService;
import ru.diasoft.spring.homework.service.QuestionService;

import java.util.List;

@ShellComponent
@Log4j2
@RequiredArgsConstructor
public class QuestionShellClient {

    private final MessageSourceService messageSourceService;

    private final QuestionProperties questionProperties;
    private final QuestionService questionService;

    private boolean questionIsSed = false;

    @ShellMethod(key = "start-test", value = "Method start test")
    public String startTest(
            @ShellOption String firstName,
            @ShellOption String lastName
    ) {

        Integer countQuestions = questionProperties.getCountQuestions();
        StringBuffer stringBuffer = new StringBuffer();

        var msg1 = messageSourceService.getMessage("prop.welcome");
        stringBuffer.append(String.format(msg1, firstName, lastName));

        var msg2 = messageSourceService.getMessage("prop.info");
        stringBuffer.append(msg2);

        var msg3 = messageSourceService.getMessage("prop.lets.go");
        stringBuffer.append(msg3);

        List<QuestionDto> questionList = questionService.findAll();

        if (countQuestions > questionList.size()) {
            countQuestions = questionList.size();
        }
        questionList = questionList.subList(0, countQuestions);

        questionList.forEach(question -> {
            stringBuffer.append("----------\n");
            var msg4 = messageSourceService.getMessage("prop.number.question");
            stringBuffer.append(String.format(msg4, question.getNumber(), question.getText()));
            question.getAnswers().forEach(answer -> {
                var msg5 = messageSourceService.getMessage("prop.number.answer");
                stringBuffer.append(String.format(msg5, answer.getNumber(), answer.getText()));
            });
        });

        questionIsSed = true;

        return stringBuffer.toString();
    }

    @ShellMethod(key = "end-test", value = "Method end test")
    @ShellMethodAvailability(value = "questionIsSed")
    public String endTest(@ShellOption("answers") String answers) {

        Integer countQuestions = questionProperties.getCountQuestions();
        Integer minRightAnswers = questionProperties.getMinRightAnswers();

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
            result = messageSourceService.getMessage("prop.answer.good");
        } else {
            result = messageSourceService.getMessage("prop.answer.bad");
        }
        questionIsSed = false;

        var msgResult = messageSourceService.getMessage("prop.end.test");
        return String.format(msgResult, countRightAnswer, result);
    }

    private Availability questionIsSed() {
        if (questionIsSed == false) {
            var msg = messageSourceService.getMessage("prop.question.is.sed");
            return Availability.unavailable(msg);
        } else {
            return Availability.available();
        }
    }

}
