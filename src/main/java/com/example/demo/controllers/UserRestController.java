package com.example.demo.controllers;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.LoginRequest;
import com.example.demo.dto.RegisterRequest;
import com.example.demo.handler.Response;
import com.example.demo.services.AccountService;

@RestController
@RequestMapping("api")
public class UserRestController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private AccountService accountService;

    @PostMapping("user/login")
    public ResponseEntity<Object> login(@RequestBody LoginRequest login)
    {
        Authentication authentication = authenticationManager.authenticate
        (new UsernamePasswordAuthenticationToken(login.getEmail(), login.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        if(authentication.isAuthenticated()){
            return Response.generate(HttpStatus.OK, "account has been log in");
        }

        return Response.generate(HttpStatus.UNAUTHORIZED, "Authentication Failed");
    }


    @PostMapping("user/register")
    public ResponseEntity<Object> register(@RequestBody RegisterRequest registerRequest){
        Boolean result = accountService.register(registerRequest);
        if(result){
            return Response.generate(HttpStatus.OK, "data has been saved");
        }
        return Response.generate(HttpStatus.INTERNAL_SERVER_ERROR,"Failed to save data");
    }

    @PostMapping("user/forgot")
    public String forgotPassword(){
        return "";
    }

    @PostMapping("profile/changePassword")
    public String changePassword(){
        return "";
    }
}
