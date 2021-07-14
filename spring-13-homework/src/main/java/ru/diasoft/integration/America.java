package ru.diasoft.integration;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import ru.diasoft.integration.domain.Oil;

import java.util.Collection;

@MessagingGateway
public interface America {

    @Gateway(requestChannel = "democracyChannel", replyChannel = "oilChannel")
    Collection<Oil> sendDemocracy(Collection<String> countries);

}
