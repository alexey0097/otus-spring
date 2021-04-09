package ru.diasoft.spring.homework.parser;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import ru.diasoft.spring.homework.dto.AnswerDto;
import ru.diasoft.spring.homework.dto.QuestionDto;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@Log4j2
public class QuestionParserImpl implements QuestionParser {

    @Override
    public Optional<QuestionDto> parseQuestion(String questionLine) {
        List<String> questionFields = Arrays.asList(questionLine.split(";"));
        if (!CollectionUtils.isEmpty(questionFields)) {
            QuestionDto questionDto = new QuestionDto();
            questionDto.setNumber(Integer.valueOf(questionFields.get(0)));
            if (questionFields.size() > 1) questionDto.setText(questionFields.get(1));
            if (questionFields.size() > 2) questionDto.setAnswers(this.parseAnswers(questionFields.get(2)));
            return Optional.of(questionDto);
        } else {
            return Optional.empty();
        }
    }

    @Override
    public List<AnswerDto> parseAnswers(String answersLine)  {
        return Arrays.asList(answersLine.split("\\|")).stream()
                .map(this::parseAnswer)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<AnswerDto> parseAnswer(String answerLine) {
        List<String> answerFields = Arrays.asList(answerLine.split("&"));
        if (!CollectionUtils.isEmpty(answerFields)) {
            AnswerDto answer = new AnswerDto();
            answer.setNumber(answerFields.get(0));
            if (answerFields.size() > 1) answer.setText(answerFields.get(1));
            if (answerFields.size() > 2) answer.setIsCorrect(Boolean.valueOf(answerFields.get(2)));
            return Optional.of(answer);
        } else {
            return Optional.empty();
        }
    }

}
