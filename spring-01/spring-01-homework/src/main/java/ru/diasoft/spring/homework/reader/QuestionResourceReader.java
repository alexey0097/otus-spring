package ru.diasoft.spring.homework.reader;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.io.Resource;
import org.springframework.util.CollectionUtils;
import ru.diasoft.spring.homework.dto.AnswerDto;
import ru.diasoft.spring.homework.dto.QuestionDto;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.nio.charset.StandardCharsets.UTF_8;

@Log4j2
@RequiredArgsConstructor
public class QuestionResourceReader implements ResourceReader<List<QuestionDto>> {

    @Override
    public List<QuestionDto> read(Resource resource) {
        List<QuestionDto> questions = new LinkedList<>();
        try(
            InputStreamReader inputStreamReader = new InputStreamReader(resource.getInputStream(), UTF_8);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader)
        ) {
            while(bufferedReader.ready()) {
                this.parseQuestion(bufferedReader.readLine())
                        .ifPresent(questions::add);
            }
        } catch (IOException e) {
            log.error(e);
        }
        return questions;
    }

    private Optional<QuestionDto> parseQuestion(String questionLine) {
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

    private List<AnswerDto> parseAnswers(String answersLine)  {
        return Arrays.asList(answersLine.split("\\|")).stream()
            .map(this::parseAnswer)
            .filter(Optional::isPresent)
            .map(Optional::get)
            .collect(Collectors.toList());
    }

    private Optional<AnswerDto> parseAnswer(String answerLine) {
        List<String> answerFields = Arrays.asList(answerLine.split("&"));
        if (!CollectionUtils.isEmpty(answerFields)) {
            AnswerDto answer = new AnswerDto();
            answer.setNumber(Integer.valueOf(answerFields.get(0)));
            if (answerFields.size() > 1) answer.setText(answerFields.get(1));
            if (answerFields.size() > 2) answer.setIsCorrect(Boolean.valueOf(answerFields.get(2)));
            return Optional.of(answer);
        } else {
            return Optional.empty();
        }
    }

}
