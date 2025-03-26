package com.datta.cardiac_arrest.controller;


import com.datta.cardiac_arrest.model.User;
import com.datta.cardiac_arrest.model.UserPrediction;
import com.datta.cardiac_arrest.service.UserPredictionService;
import com.datta.cardiac_arrest.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserCredentials {

    @Autowired
    private UserService service;

    @Autowired
    private UserPredictionService userPredictionService;

    @PostMapping("update_user")
    public ResponseEntity<User> update_user(@RequestBody User user) {
        return ResponseEntity.ok(service.updateUser(user));
    }

    @GetMapping("get_user")
    public ResponseEntity<User> get_user(HttpServletRequest request){
        User user=service.getUser(request);
        return ResponseEntity.ok(user);
    }

    @GetMapping("get_health_data")
    public ResponseEntity<List<UserPrediction>> get_health_data(HttpServletRequest request){
        return ResponseEntity.ok(userPredictionService.healthDta(request));
    }
}
