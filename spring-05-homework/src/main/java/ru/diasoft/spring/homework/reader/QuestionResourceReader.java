package ru.diasoft.spring.homework.reader;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import ru.diasoft.spring.homework.dto.QuestionDto;
import ru.diasoft.spring.homework.parser.QuestionParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import static java.nio.charset.StandardCharsets.UTF_8;

@Component
@Log4j2
@RequiredArgsConstructor
public class QuestionResourceReader implements ResourceReader<List<QuestionDto>> {

    private final QuestionParser questionParser;

    @Override
    public List<QuestionDto> read(String url) {
        List<QuestionDto> questions = new LinkedList<>();
        Resource resource = new ClassPathResource(url);
        if (resource.exists()) {
            try(
                InputStreamReader inputStreamReader = new InputStreamReader(resource.getInputStream(), UTF_8);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader)
            ) {
                while(bufferedReader.ready()) {
                    Optional<QuestionDto> question = questionParser.parseQuestion(bufferedReader.readLine());
                    question.ifPresent(questions::add);
                }
            } catch (IOException e) {
                log.error(e);
            }
        }
        return questions;
    }



}
