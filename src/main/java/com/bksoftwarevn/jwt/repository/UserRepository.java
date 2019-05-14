package com.bksoftwarevn.jwt.repository;


import com.bksoftwarevn.jwt.entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<AppUser, Integer> {

    AppUser findByUsernameAndPassword(String username, String password);

    AppUser findByUsername(String username);
}
