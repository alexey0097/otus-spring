package ru.diasoft.spring.homework.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.io.Resource;
import ru.diasoft.spring.homework.dto.QuestionDto;
import ru.diasoft.spring.homework.reader.QuestionResourceReader;

import java.util.Collections;
import java.util.List;

@Log4j2
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {

    private final QuestionResourceReader questionResourceReader;
    private final Resource questionResource;

    @Override
    public List<QuestionDto> findAll() {
        return questionResource.exists() ? questionResourceReader.read(questionResource) : Collections.emptyList();
    }

}
