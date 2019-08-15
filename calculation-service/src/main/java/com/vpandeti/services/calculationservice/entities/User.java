package com.vpandeti.services.calculationservice.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    @Column(length = 20)
    private String firstName;
    @Column(length = 20)
    private String lastName;
    @Column(length = 20)
    private String username;
    @Column(length = 64)
    private String password;
    @Column(length = 254)
    private String email;
    private long createdTime;
}
