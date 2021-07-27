package ru.diasoft.app;

import lombok.extern.log4j.Log4j2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.ApplicationContext;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import ru.diasoft.app.channel.pub.MessageGateway;
import ru.diasoft.app.channel.pub.ProducerChannels;
import ru.diasoft.app.channel.sub.ConsumerChannels;
import ru.diasoft.app.dto.AuthorDto;
import ru.diasoft.app.service.AuthorService;

@Log4j2
@SpringBootApplication
@EnableBinding({ConsumerChannels.class, ProducerChannels.class})
public class MyApplication {

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(MyApplication.class, args);

        AuthorService authorService = ctx.getBean(AuthorService.class);
        MessageGateway messageGateway = ctx.getBean(MessageGateway.class);

        AuthorDto authorDto = authorService.findById(1L)
                .orElseThrow();

        Message<AuthorDto> msg = MessageBuilder.withPayload(authorDto).build();
        messageGateway.publisherAuthor(msg);
    }

}
