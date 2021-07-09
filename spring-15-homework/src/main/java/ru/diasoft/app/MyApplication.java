package ru.diasoft.app;

import lombok.extern.log4j.Log4j2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import ru.diasoft.app.service.CountryService;

@Log4j2
@SpringBootApplication
public class MyApplication {

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(MyApplication.class, args);

        CountryService countryService = ctx.getBean(CountryService.class);

        log.info("-------countryClient.findByName()--------");
        log.info(countryService.findByName("Russian Federation"));

        log.info("-------countryClient.findAll()--------");
        countryService.findAll().forEach(log::info);
    }

}
