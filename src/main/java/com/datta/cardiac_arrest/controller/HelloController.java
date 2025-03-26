package com.datta.cardiac_arrest.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@Slf4j
public class HelloController {
	@Autowired
	AuthenticationManager authenticationManager;

	@GetMapping("hello")
	public String greet() {
		return "Hello World ";
	}
	
	@GetMapping("about")
	public String about(HttpServletRequest request) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        return authentication.getName();
	}
}
