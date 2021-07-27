package ru.diasoft.spring.homework;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import ru.diasoft.spring.homework.channel.pub.ProducerChannels;
import ru.diasoft.spring.homework.channel.sub.ConsumerChannels;

@SpringBootApplication
@EnableBinding({ConsumerChannels.class, ProducerChannels.class})
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}
