package ru.diasoft.spring.homework;

import lombok.extern.log4j.Log4j2;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.diasoft.spring.homework.dto.QuestionDto;
import ru.diasoft.spring.homework.service.QuestionService;

import java.util.List;

@Log4j2
public class Main {

    public static void main(String[] args) {
        final var context = new ClassPathXmlApplicationContext("/spring-context.xml");
        QuestionService questionService = context.getBean(QuestionService.class);
        List<QuestionDto> questions = questionService.findAll();
        questions.forEach(log::info);

        // Данная операция, в принципе не нужна.
        // Мы не работаем пока что с БД, а Spring Boot сделает закрытие за нас
        // Подробности - через пару занятий
        context.close();
    }
}
