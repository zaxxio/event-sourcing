package org.wsd.query;

import lombok.Data;

import java.util.UUID;

@Data
public class GetUserByIdQuery {
    private UUID userId;
}
