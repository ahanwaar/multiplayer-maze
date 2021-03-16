package com.worldnavigator.multiplayermaze.web.controller;

import com.worldnavigator.multiplayermaze.web.entity.User;
import com.worldnavigator.multiplayermaze.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {
    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> list = userService.retrieveUsers();
        return new ResponseEntity<List<User>>(list, HttpStatus.OK);
    }
}
