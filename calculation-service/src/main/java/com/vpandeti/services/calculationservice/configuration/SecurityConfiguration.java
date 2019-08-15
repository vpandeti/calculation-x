package com.vpandeti.services.calculationservice.configuration;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;

public class SecurityConfiguration {


    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeRequests().antMatchers("/").permitAll();
    }

}
