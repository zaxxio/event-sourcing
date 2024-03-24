package org.wsd.bootloader;

import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.wsd.commands.CreateUserCommand;
import org.wsd.domain.UserEntity;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class BootLoader implements CommandLineRunner {
    private final CommandGateway commandGateway;
    @Override
    public void run(String... args) throws Exception {
        final UUID userId = UUID.randomUUID();

        final CreateUserCommand createUserCommand = new CreateUserCommand();
        createUserCommand.setUserId(userId);
        createUserCommand.setUser(
                UserEntity.builder()
                        .userId(userId)
                        .username("username")
                        .password("password")
                        .build()
        );

        this.commandGateway.send(createUserCommand);
    }
}
