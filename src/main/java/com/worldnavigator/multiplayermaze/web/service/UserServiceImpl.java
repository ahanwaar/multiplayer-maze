package com.worldnavigator.multiplayermaze.web.service;

import com.worldnavigator.multiplayermaze.web.entity.User;
import com.worldnavigator.multiplayermaze.web.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserRepository userRepository;

    @Override
    public List<User> retrieveUsers() {
        return (List<User>) userRepository.findAll();
    }

    @Override
    public boolean checkIfExists(String username) {
        return userRepository.existsById(username);
    }

    @Override
    public User getUserByUsername(String username) {
        return userRepository.findById(username).get();
    }

    public void saveUser(User user){
        userRepository.save(user);
    }
}
