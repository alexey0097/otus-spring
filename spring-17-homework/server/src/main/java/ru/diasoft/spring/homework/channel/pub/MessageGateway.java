package ru.diasoft.spring.homework.channel.pub;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.messaging.Message;
import ru.diasoft.spring.homework.entity.Author;

@MessagingGateway
public interface MessageGateway {

    @Gateway(requestChannel = ProducerChannelsConstants.BINDING_AUTHOR_PUB_CHANNEL)
    void publisherAuthor(Message<Author> msg);

}
