package org.wsd.commands;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.axonframework.eventhandling.DisallowReplay;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;
import org.wsd.domain.UserEntity;
import org.wsd.events.UserCreatedEvent;
import org.wsd.repository.UserRepository;

@Log4j2
@Component
@RequiredArgsConstructor
public class UserEventHandler {
    private final UserRepository userRepository;

    @EventHandler
    @DisallowReplay
    public void on(UserCreatedEvent userCreatedEvent) {
        log.info("User created event : " + userCreatedEvent);
        UserEntity save = userRepository.save(userCreatedEvent.getUser());
        log.info("User saved " + save);
    }

}
