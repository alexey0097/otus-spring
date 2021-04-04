package ru.diasoft.spring.homework.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VariantAnswerDto {
    protected String number;
    protected String text;
    protected Boolean isCorrect;
    protected String answerDescription;
}
