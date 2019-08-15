package com.vpandeti.services.calculationservice.responses;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors
public class LoginResponse {
    private String usrename;
    private String status;
    private String error;
}
