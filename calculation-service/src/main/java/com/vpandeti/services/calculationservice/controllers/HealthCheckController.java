package com.vpandeti.services.calculationservice.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheckController {

    @GetMapping("/health/check")
    public String checkHealth() {
        return "Up";
    }
}
