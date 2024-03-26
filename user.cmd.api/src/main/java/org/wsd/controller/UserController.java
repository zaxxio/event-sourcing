package org.wsd.controller;

import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.wsd.commands.CreateUserCommand;
import org.wsd.domain.UserEntity;


import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final CommandGateway commandGateway;

    @PostMapping("/api/users")
    public CompletableFuture<CreateUserCommand> createUser(@RequestBody UserEntity user) {
        final CreateUserCommand createUserCommand = new CreateUserCommand();
        createUserCommand.setUserId(UUID.randomUUID());
        createUserCommand.setUser(user);
        return commandGateway.send(createUserCommand);
    }

}
