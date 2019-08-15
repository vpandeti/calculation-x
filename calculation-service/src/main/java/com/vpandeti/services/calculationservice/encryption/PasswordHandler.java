package com.vpandeti.services.calculationservice.encryption;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class PasswordHandler {

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public PasswordHandler() {
        bCryptPasswordEncoder = new BCryptPasswordEncoder();
    }

    public String encodePassword(String password) {
        return bCryptPasswordEncoder.encode(password);
    }

    public boolean comparePasswords(String password, String encodedPassword) {
        return bCryptPasswordEncoder.matches(password, encodedPassword);
    }
}
