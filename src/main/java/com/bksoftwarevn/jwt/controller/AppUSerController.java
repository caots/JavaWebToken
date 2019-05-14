package com.bksoftwarevn.jwt.controller;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.bksoftwarevn.jwt.entities.AppUser;
import com.bksoftwarevn.jwt.security.SecurityConstants;
import com.bksoftwarevn.jwt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("api/v1/admin/")

public class AppUSerController {

    @Autowired
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping("user")
    public ResponseEntity<List<AppUser>> findAll() {
        return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
    }

//    @PostMapping("/api/v1/admin/user/sign-up")
//    public ResponseEntity<Object> signUp(@RequestBody AppUser user) {
//        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
//        boolean result = userService.save(user);
//
//        if(result) return new ResponseEntity<>(user, HttpStatus.OK);
//
//        return new ResponseEntity<>("nope", HttpStatus.BAD_REQUEST);
//    }

}
