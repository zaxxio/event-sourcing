package org.wsd.domain;


import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class UserEntity {
    private UUID userId;
    private String username;
    private String password;
}
