package com.datta.cardiac_arrest.controller;


import com.datta.cardiac_arrest.model.User;
import com.datta.cardiac_arrest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserCredentials {

    @Autowired
    private UserService service;

    @PostMapping("update_user")
    public ResponseEntity<User> update_user(@RequestBody User user) {
        return ResponseEntity.ok(service.updateUser(user));
    }
}
