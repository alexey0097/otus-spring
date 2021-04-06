package ru.diasoft.spring.homework.parser;

import ru.diasoft.spring.homework.dto.QuestionDto;
import ru.diasoft.spring.homework.dto.AnswerDto;

import java.util.List;
import java.util.Optional;

public interface QuestionParser {

    Optional<QuestionDto> parseQuestion(String questionLine);
    List<AnswerDto> parseAnswers(String answersLine);
    Optional<AnswerDto> parseAnswer(String answerLine);

}
