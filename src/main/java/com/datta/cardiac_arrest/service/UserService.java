package com.datta.cardiac_arrest.service;

import com.datta.cardiac_arrest.constant.ErrorMessages;
import com.datta.cardiac_arrest.exception.BadCredentialsException;
import com.datta.cardiac_arrest.exception.UserAlreadyExistsException;
import com.datta.cardiac_arrest.exception.UserNotFoundException;
import com.datta.cardiac_arrest.model.User;
import com.datta.cardiac_arrest.model.UserPrediction;
import com.datta.cardiac_arrest.repo.UserRepo;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
        User user1 = repo.findByUsername(user.getUsername());
        if (user1 != null) {
            throw new UserAlreadyExistsException(
                    ErrorMessages.USER_ALREADY_EXISTS.getErrorMessage(),
                    ErrorMessages.USER_ALREADY_EXISTS.getErrorCode()
            );

        }else {
            user.setPassword(encoder.encode(user.getPassword()));
            user.setRoles(new ArrayList<>(List.of("USER")));


            return repo.save(user);
        }

    }

    public User getUser(HttpServletRequest request){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return repo.findByUsername( authentication.getName());

    }

    public String login(User user){
        User user1 = repo.findByUsername(user.getUsername());
        if (user1 != null) {
            Authentication authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));

            if(authentication.isAuthenticated()) {
                return jwtService.generateToken(user.getUsername());
            }else{
                throw  new BadCredentialsException(
                        ErrorMessages.PASSWORD_NOT_MATCHED.getErrorMessage(),
                        ErrorMessages.PASSWORD_NOT_MATCHED.getErrorCode()
                );
            }

        }else{
            throw  new UserNotFoundException(
                    ErrorMessages.USER_NOT_FOUND.getErrorMessage(),
                    ErrorMessages.USER_NOT_FOUND.getErrorCode()
            );

        }

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
        }else{
            throw  new UserNotFoundException(
                    ErrorMessages.USER_NOT_FOUND.getErrorMessage(),
                    ErrorMessages.USER_NOT_FOUND.getErrorCode()
            );

        }

    }

    public void updateReport(String userName, UserPrediction userPrediction){
        User user=repo.findByUsername(userName);
        if(user!=null){
            if (user.getPredictions() == null) {
                user.setPredictions(new ArrayList<>());
            }
            List<UserPrediction> list=user.getPredictions();
            list.add(userPrediction);
            user.setPredictions(list);
            repo.save(user);
        }

    }

}
