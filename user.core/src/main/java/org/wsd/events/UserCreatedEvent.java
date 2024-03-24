package org.wsd.events;

import lombok.Builder;
import lombok.Data;
import org.wsd.domain.UserEntity;

import java.util.UUID;

@Data
@Builder
public class UserCreatedEvent {
    private UUID userId;
    private UserEntity user;
}
