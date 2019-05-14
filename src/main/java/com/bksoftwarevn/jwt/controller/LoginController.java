package com.bksoftwarevn.jwt.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.bksoftwarevn.jwt.entities.AppUser;
import com.bksoftwarevn.jwt.security.SecurityConstants;
import com.bksoftwarevn.jwt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@RestController
@RequestMapping("api/v1/public/login")
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping
    public ResponseEntity<Object> login(@RequestBody AppUser loginForm, HttpServletResponse response) {

//        String password = bCryptPasswordEncoder.encode(loginForm.getPassword());
//
//        System.out.println(password);

        AppUser appAdmin = userService.findByUsernameAndPass(loginForm.getUsername(), loginForm.getPassword());
        if (appAdmin == null) return new ResponseEntity<>("false", HttpStatus.BAD_REQUEST);
        else {
            String token = JWT.create()
                    .withSubject(appAdmin.getUsername())
                    .withExpiresAt(new Date(System.currentTimeMillis() + SecurityConstants.EXPIRATION_TIME))
                    .sign(Algorithm.HMAC512(SecurityConstants.SECRET.getBytes()));
            response.addHeader(SecurityConstants.HEADER_STRING, SecurityConstants.TOKEN_PREFIX + token);
            return new ResponseEntity<>(token, HttpStatus.OK);
        }
    }
}
