package ru.diasoft.app;

import lombok.extern.log4j.Log4j2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import ru.diasoft.app.service.AuthorService;

@Log4j2
@SpringBootApplication
public class MyApplication {

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(MyApplication.class, args);

        AuthorService authorService = ctx.getBean(AuthorService.class);

        log.info("-------authorService.findById()--------");
        log.info(authorService.findById(1L));

        log.info("-------authorService.findAll()--------");
        authorService.findAll().forEach(log::info);
    }

}
