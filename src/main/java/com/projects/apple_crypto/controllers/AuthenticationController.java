package com.projects.apple_crypto.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projects.apple_crypto.dtos.LoginResponse;
import com.projects.apple_crypto.dtos.LoginUserDto;
import com.projects.apple_crypto.dtos.RegisterUserDto;
import com.projects.apple_crypto.entities.User;
import com.projects.apple_crypto.security.UserService;
import com.projects.apple_crypto.security.jwt.JwtService;

@RequestMapping("/auth")
@RestController
public class AuthenticationController {
    @Autowired
    private JwtService jwtService;
    
    @Autowired
    private UserService authenticationService;

    @Autowired
    private AuthenticationManager authenticationManager;

    // public AuthenticationController(JwtService jwtService, UserService authenticationService) {
    //     this.jwtService = jwtService;
    //     this.authenticationService = authenticationService;
    // }

    @PostMapping("/signup")
    public ResponseEntity<User> register(@RequestBody RegisterUserDto registerUserDto) {
        User registeredUser = authenticationService.signup(registerUserDto);

        return ResponseEntity.ok(registeredUser);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticate(@RequestBody LoginUserDto loginUserDto) {
        User authenticatedUser = authenticationService.authenticate(loginUserDto, authenticationManager);

        String jwtToken = jwtService.generateToken(authenticatedUser);

        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setToken(jwtToken);
        loginResponse.setExpiration(jwtService.getExpirationTime());

        return ResponseEntity.ok(loginResponse);
    }

    @GetMapping("/check")
    public String check() {
        return "Success";
    }
}
