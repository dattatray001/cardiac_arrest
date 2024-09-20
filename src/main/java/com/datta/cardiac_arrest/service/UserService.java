package com.datta.cardiac_arrest.service;

import com.datta.cardiac_arrest.model.User;
import com.datta.cardiac_arrest.repo.UserRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@Slf4j
public class UserService {

    @Autowired
    private UserRepo repo;
    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    @Autowired
    private JwtService jwtService;

    @Autowired
    AuthenticationManager authenticationManager;

    public User saveUser(User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        user.setRoles(new ArrayList<>(List.of("USER")));

        return repo.save(user);

    }

    public String login(User user){
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));

        if(authentication.isAuthenticated())
            return jwtService.generateToken(user.getUsername());
        else
            return "Login Failed";
    }


    public User updateUser(User user) {
        User user1 = repo.findByUsername(user.getUsername());
        if (user1 != null) {
            user1.setRoles(user.getRoles());
            user1.setName(user.getName());
            user1.setUsername(user.getUsername());
            user1.setPassword(encoder.encode(user.getPassword()));
            log.info("updated successful");
            return repo.save(user1);
        }
        return null;
    }

}
