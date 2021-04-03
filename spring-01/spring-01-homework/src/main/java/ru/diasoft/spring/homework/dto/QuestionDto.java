package ru.diasoft.spring.homework.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestionDto {
    protected Integer number;
    protected String text;
    protected List<AnswerDto> answers = new ArrayList<>();
}
