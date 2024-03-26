package org.wsd.handler;

import lombok.RequiredArgsConstructor;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;
import org.wsd.domain.UserEntity;
import org.wsd.query.GetEveryUserQuery;
import org.wsd.query.GetUserByIdQuery;
import org.wsd.repository.UserRepository;

import java.util.List;

@Component
@RequiredArgsConstructor
public class UserQueryHandler {
    private final UserRepository userRepository;

    @QueryHandler
    public List<UserEntity> getAllUser(GetEveryUserQuery everyUserQuery) {
        return userRepository.findAll();
    }

    @QueryHandler
    public UserEntity getUserById(GetUserByIdQuery userByIdQuery) {
        return userRepository.findById(userByIdQuery.getUserId()).orElseThrow(() -> new RuntimeException("user not found"));
    }
}
