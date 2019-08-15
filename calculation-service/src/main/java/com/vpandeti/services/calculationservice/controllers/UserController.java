package com.vpandeti.services.calculationservice.controllers;

import com.vpandeti.services.calculationservice.entities.User;
import com.vpandeti.services.calculationservice.responses.LoginResponse;
import com.vpandeti.services.calculationservice.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/user")
    public ResponseEntity<LoginResponse> createUser(@RequestBody User user) {
        userService.createUser(user);
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setUsrename(user.getUsername());
        loginResponse.setStatus("Created");
        loginResponse.setError("");
        return new ResponseEntity<>(loginResponse, HttpStatus.CREATED);
    }

    @PostMapping("/user/{username}/login")
    public ResponseEntity<LoginResponse> login(@PathVariable String username, @RequestBody User user) {
        userService.login(username, user.getPassword());
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setUsrename(username);
        loginResponse.setStatus("Logged in");
        loginResponse.setError("");
        return new ResponseEntity<>(loginResponse, HttpStatus.OK);
    }

}
