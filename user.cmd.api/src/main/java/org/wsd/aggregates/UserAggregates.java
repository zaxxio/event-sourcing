package org.wsd.aggregates;

import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.wsd.commands.CreateUserCommand;
import org.wsd.domain.UserEntity;
import org.wsd.events.UserCreatedEvent;

import java.io.Serializable;
import java.util.UUID;

@Aggregate
@NoArgsConstructor
public class UserAggregates implements Serializable {
    @AggregateIdentifier
    private UUID userId;
    private String username;
    private String password;

    @CommandHandler
    public UserAggregates(CreateUserCommand createUserCommand) {
        final UserEntity user = createUserCommand.getUser();
        user.setUserId(user.getUserId());
        user.setUsername(createUserCommand.getUser().getUsername());
        user.setPassword(createUserCommand.getUser().getPassword());

        final UserCreatedEvent event = UserCreatedEvent
                .builder()
                .userId(user.getUserId())
                .user(user)
                .build();

        AggregateLifecycle.apply(event);
    }

    @EventSourcingHandler
    public void on(UserCreatedEvent userCreatedEvent) {
        this.userId = userCreatedEvent.getUserId();
        this.username = userCreatedEvent.getUser().getUsername();
        this.password = userCreatedEvent.getUser().getPassword();
    }

}
