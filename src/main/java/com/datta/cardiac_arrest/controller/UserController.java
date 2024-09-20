package com.datta.cardiac_arrest.controller;


import com.datta.cardiac_arrest.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.datta.cardiac_arrest.model.User;
import com.datta.cardiac_arrest.service.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService service;


	
	@PostMapping("register")
	public User register(@RequestBody User user) {
	  return service.saveUser(user);
	}

	@PostMapping("login")
	public String login(@RequestBody User user){

		return service.login(user);


	}

}
