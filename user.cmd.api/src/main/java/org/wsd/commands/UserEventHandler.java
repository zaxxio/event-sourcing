package org.wsd.commands;

import lombok.extern.log4j.Log4j2;
import org.axonframework.eventhandling.DisallowReplay;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;
import org.wsd.events.UserCreatedEvent;

@Log4j2
@Component
public class UserEventHandler {

    @EventHandler
    @DisallowReplay
    public void on(UserCreatedEvent userCreatedEvent) {
        log.info("User created event : " + userCreatedEvent);
    }

}
