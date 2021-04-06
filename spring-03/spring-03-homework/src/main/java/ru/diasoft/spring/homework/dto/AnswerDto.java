package ru.diasoft.spring.homework.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnswerDto {
    private String number;
    private String text;
    private Boolean isCorrect;
    private String answerDescription;
}
