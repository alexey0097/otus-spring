package ru.diasoft.app.channel.sub;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface ConsumerChannels {

    @Input(ConsumerChannelsConstants.BINDING_AUTHOR_SUB_CHANNEL)
    SubscribableChannel subscribeAuthor();
    
}