package ru.diasoft.spring.homework.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import ru.diasoft.spring.homework.config.AppConfig;
import ru.diasoft.spring.homework.config.QuestionProperties;
import ru.diasoft.spring.homework.dto.QuestionDto;
import ru.diasoft.spring.homework.reader.QuestionResourceReader;

import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
public class QuestionServiceImpl implements QuestionService {

    private final QuestionProperties questionProperties;

    private final QuestionResourceReader questionResourceReader;

    @Override
    public List<QuestionDto> findAll() {
        return questionResourceReader.read(questionProperties.getPathToQuestions());
    }

}
