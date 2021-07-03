package ru.diasoft.integration.config;

import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.MessageChannels;
import org.springframework.integration.dsl.Pollers;
import org.springframework.integration.scheduling.PollerMetadata;
import org.springframework.messaging.PollableChannel;
import org.springframework.messaging.SubscribableChannel;

@Log4j2
@Configuration
public class ChannelConfig {

    @Bean
    public PollableChannel democracyChannel() {
        return MessageChannels.queue(10).get();
    }

    @Bean
    public SubscribableChannel massMediaChannel() {
        SubscribableChannel channel = MessageChannels
                .publishSubscribe()
                .get();

        channel.subscribe( msg -> {
            log.info("Было выслано 5 тонн демократии в страну - " + msg.getPayload());
        });

        return channel;
    }

    @Bean
    public SubscribableChannel spyChannel() {
        SubscribableChannel channel = MessageChannels
                .publishSubscribe()
                .get();

        channel.subscribe( msg -> {
            log.info("ВНИМАНИЕ!!! Шпионы обнаружили нефть!");
            log.info(msg.getPayload());
        });

        return channel;
    }

    @Bean
    public SubscribableChannel oilChannel() {
        return MessageChannels
                .publishSubscribe()
                .get();
    }

    @Bean(name = PollerMetadata.DEFAULT_POLLER)
    public PollerMetadata poller() {
        return Pollers.fixedRate( 100 )
                .maxMessagesPerPoll( 2 ).get();
    }

    @Bean
    public IntegrationFlow cafeFlow() {
        return IntegrationFlows.from( "democracyChannel" )
                .split()
                .channel("massMediaChannel")
                .handle( "democracyServiceImpl", "sendDemocracyToCountry")
                .channel("spyChannel")
                .aggregate()
                .channel("oilChannel")
                .get();
    }


}
