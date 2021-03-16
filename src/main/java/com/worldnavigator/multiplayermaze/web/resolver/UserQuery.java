package com.worldnavigator.multiplayermaze.web.resolver;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.worldnavigator.multiplayermaze.web.entity.User;
import com.worldnavigator.multiplayermaze.web.repository.UserRepository;
import org.springframework.stereotype.Component;

@Component
public class UserQuery implements GraphQLQueryResolver {
    private UserRepository userRepository;

    public UserQuery(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Iterable<User> findAllUsers() {
        return userRepository.findAll();
    }

}
