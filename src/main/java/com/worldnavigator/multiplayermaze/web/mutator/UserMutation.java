package com.worldnavigator.multiplayermaze.web.mutator;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.worldnavigator.multiplayermaze.web.entity.User;
import com.worldnavigator.multiplayermaze.web.repository.UserRepository;
import org.springframework.stereotype.Component;

@Component
public class UserMutation implements GraphQLMutationResolver {
    private UserRepository userRepository;

    public UserMutation(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean loginUser(String username, String password){
        if(userRepository.existsById(username)){
            User user = userRepository.findById(username).get();
            if(user.getPassword().equals(password)){
                return true;
            }
        }
        return false;
    }

    public Boolean registerUser(String username, String password) {
        User user = new User(username, password);
        if(!userRepository.existsById(username)){
            userRepository.save(user);
            return true;
        }
        return false;
    }

    public boolean deleteUser(String username) {
        userRepository.deleteById(username);
        return true;
    }
}
