package org.wsd.aggregates;

import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.wsd.commands.CreateUserCommand;
import org.wsd.events.UserCreatedEvent;

import java.io.Serializable;
import java.util.UUID;

@Aggregate
@NoArgsConstructor
public class UserAggregates implements Serializable {
    @AggregateIdentifier
    private UUID userId;

    @CommandHandler
    public UserAggregates(CreateUserCommand createUserCommand) {
        final UserCreatedEvent event = UserCreatedEvent
                .builder()
                .userId(UUID.randomUUID())
                .user(createUserCommand.getUser())
                .build();
        AggregateLifecycle.apply(event);
    }

    @EventSourcingHandler
    public void on(UserCreatedEvent userCreatedEvent) {
        this.userId = userCreatedEvent.getUserId();
    }

}
