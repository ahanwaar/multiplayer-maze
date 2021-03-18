package com.worldnavigator.multiplayermaze.web.service;

import com.worldnavigator.multiplayermaze.web.entity.User;

import java.util.List;

public interface UserService {
    List<User> retrieveUsers();
    boolean checkIfExists(String username);
    User getUserByUsername(String username);
    void saveUser(User user);
}
