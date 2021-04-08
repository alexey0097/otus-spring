package ru.diasoft.spring.homework;

import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.shell.Bootstrap;

import java.io.IOException;

@Configuration
@ComponentScan
@Log4j2
public class Main {

    public static void main(String[] args) throws IOException {
        Bootstrap.main(args);
    }
}
