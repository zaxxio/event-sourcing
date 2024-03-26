package org.wsd.controller;

import lombok.RequiredArgsConstructor;
import org.axonframework.messaging.responsetypes.ResponseType;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.wsd.domain.UserEntity;
import org.wsd.query.GetEveryUserQuery;
import org.wsd.query.GetUserByIdQuery;
import org.wsd.repository.UserRepository;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserQueryController {

    private final QueryGateway queryGateway;
    private final UserRepository userRepository;

    @GetMapping
    public List<UserEntity> getUser() {
        GetEveryUserQuery getEveryUserQuery = new GetEveryUserQuery();
        return queryGateway.query(getEveryUserQuery, ResponseTypes.multipleInstancesOf(UserEntity.class)).join();
    }

    @GetMapping("/all")
    public List<UserEntity> getUser1() {
        return userRepository.findAll();
    }

    @GetMapping("/{userId}")
    public UserEntity getUser(@PathVariable("userId") UUID userId) {
        GetUserByIdQuery getUserByIdQuery = new GetUserByIdQuery();
        getUserByIdQuery.setUserId(userId);
        return queryGateway.query(getUserByIdQuery, ResponseTypes.instanceOf(UserEntity.class)).join();
    }

}
