package ru.diasoft.app.channel.sub;

import lombok.extern.log4j.Log4j2;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;
import ru.diasoft.app.dto.AuthorDto;

@Configuration
@Log4j2
public class MessageListener {

    @StreamListener(ConsumerChannelsConstants.BINDING_AUTHOR_SUB_CHANNEL)
    public void dsOnAfterMarketManPOST(Message<AuthorDto> message) {
        log.info(message);
    }
    
}
