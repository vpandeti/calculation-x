package com.vpandeti.services.calculationservice.repositories;

import com.vpandeti.services.calculationservice.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Integer> {

    @Query(value = "SELECT * FROM user u WHERE u.username=:username", nativeQuery = true)
    User findUserByCredentials(String username);

    @Query(value = "SELECT * FROM user u WHERE u.username=:username AND u.password=:password", nativeQuery = true)
    User findUserByCredentials(String username, String password);
}
