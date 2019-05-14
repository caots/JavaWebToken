package com.bksoftwarevn.jwt.service;

import com.bksoftwarevn.jwt.entities.AppUser;
import com.bksoftwarevn.jwt.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<AppUser> findAll() {
        return userRepository.findAll();
    }

    public AppUser findByUsernameAndPass(String username, String password) {
        return userRepository.findByUsernameAndPassword(username,password);
    }


    public AppUser findByUsername(String username) {
        return  userRepository.findByUsername(username);
    }

    public boolean save(AppUser user){
        userRepository.save(user);
        return true;
    }
}
