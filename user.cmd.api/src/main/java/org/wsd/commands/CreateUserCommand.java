package org.wsd.commands;

import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;
import org.wsd.domain.UserEntity;

import java.io.Serializable;
import java.util.UUID;

@Data
public class CreateUserCommand implements Serializable {
    @TargetAggregateIdentifier
    private UUID userId;
    private UserEntity user;
}
