package ru.diasoft.integration;

import lombok.extern.log4j.Log4j2;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.messaging.SubscribableChannel;
import ru.diasoft.integration.domain.Oil;
import ru.diasoft.integration.service.DemocracyServiceImpl;

import java.util.Collection;
import java.util.List;

@Log4j2
@SpringBootApplication
public class IntegrationApplication {

    public static void main(String[] args) {
        AbstractApplicationContext ctx = new AnnotationConfigApplicationContext(IntegrationApplication.class);

        America america = ctx.getBean(America.class);

        america.sendDemocracy(List.of(
                DemocracyServiceImpl.COUNTRY_RUSSIA,
                DemocracyServiceImpl.COUNTRY_IRAN,
                DemocracyServiceImpl.COUNTRY_AFGHANISTAN
        ));

    }

}
