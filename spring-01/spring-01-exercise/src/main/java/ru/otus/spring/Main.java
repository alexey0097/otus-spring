package ru.otus.spring;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.otus.spring.domain.Person;
import ru.otus.spring.service.PersonService;

public class Main {

    public static void main(String[] args) {
        final var context = new ClassPathXmlApplicationContext("/spring-context.xml");

        PersonService service = context.getBean(PersonService.class);
        Person ivan = service.getByName("Ivan");

        final var msg = String.format("name: %s, age: %s", ivan.getName(), ivan.getAge());
        System.out.println(msg);
    }
}
