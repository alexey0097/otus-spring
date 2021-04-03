package ru.diasoft.spring.homework.service;

import ru.diasoft.spring.homework.dto.QuestionDto;

import java.util.List;

public interface QuestionService {

    List<QuestionDto> findAll();

}
