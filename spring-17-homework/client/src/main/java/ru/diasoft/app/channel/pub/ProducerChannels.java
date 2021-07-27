package ru.diasoft.app.channel.pub;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface ProducerChannels {

    @Output(ProducerChannelsConstants.BINDING_AUTHOR_PUB_CHANNEL)
    MessageChannel publisherAuthor();
    
}