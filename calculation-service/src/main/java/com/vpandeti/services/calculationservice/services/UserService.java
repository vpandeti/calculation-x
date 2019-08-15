package com.vpandeti.services.calculationservice.services;

import com.vpandeti.services.calculationservice.encryption.PasswordHandler;
import com.vpandeti.services.calculationservice.entities.User;
import com.vpandeti.services.calculationservice.exceptions.AuthenticationFailedException;
import com.vpandeti.services.calculationservice.exceptions.UserAlreadyRegisteredException;
import com.vpandeti.services.calculationservice.exceptions.UserNotFoundException;
import com.vpandeti.services.calculationservice.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Service
@Slf4j
public class UserService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordHandler passwordHandler;

    public User createUser(User user) {
        if(userRepository.findUserByCredentials(user.getUsername()) != null) {
            throw new UserAlreadyRegisteredException("User already registered");
        }
        user.setPassword(passwordHandler.encodePassword(user.getPassword()));
        log.info("Creating user with Username={}", user.getUsername());

        User newUser = userRepository.save(user);
        log.info("Created user with Username={}", user.getUsername());

        return newUser;
    }

    public boolean login(String username, String password) {
        User user = userRepository.findUserByCredentials(username);
        if(user == null) {
            throw new AuthenticationFailedException("Authentication failed");
        }

        String encodedPassword = user.getPassword();
        boolean isSuccessful = passwordHandler.comparePasswords(password, encodedPassword);
        log.info("Username={}, Found={}", username, isSuccessful);
        if(!isSuccessful) {
            throw new AuthenticationFailedException("Authentication failed");
        }
        return isSuccessful;
    }

    public boolean findUserByCredentials(String authorization) {
        String username = "";
        String password = "";
        try {
            if (null == authorization) throw new AuthenticationFailedException("Authentication failed");
            if (!authorization.startsWith("Basic ")) throw new AuthenticationFailedException("Invalid authentication");
            String encodedCredentials = authorization.substring("Basic".length()).trim();
            byte[] credentialsInBytes = Base64.getDecoder().decode(encodedCredentials);
            String[] credentials = new String(credentialsInBytes, StandardCharsets.UTF_8).split(":");
            username = credentials[0];
            password = credentials[1];
        } catch (Exception e) {
            log.error("Exception={}, StackTrace={}", e.getClass().getCanonicalName(), ExceptionUtils.getStackTrace(e));
            throw new AuthenticationFailedException("Authentication exception: " + e.getMessage());
        }
        boolean isSuccessful = login(username, password);
        if(!isSuccessful) {
            throw new AuthenticationFailedException("Authentication failed");
        }
        return isSuccessful;
    }

}
