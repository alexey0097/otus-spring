package ru.diasoft.spring.homework.channel.sub;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.Message;
import org.springframework.messaging.SubscribableChannel;
import ru.diasoft.spring.homework.entity.Author;

public interface ConsumerChannels {

    @Input(ConsumerChannelsConstants.BINDING_AUTHOR_SUB_CHANNEL)
    SubscribableChannel subscribeAuthor();
    
}