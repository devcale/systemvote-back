package com.systemvote.systemvoteback.controller;

import com.systemvote.systemvoteback.dto.UserDTO;
import com.systemvote.systemvoteback.model.LoginRequest;
import com.systemvote.systemvoteback.model.LoginResponse;
import com.systemvote.systemvoteback.security.JwtIssuer;
import com.systemvote.systemvoteback.service.AuthService;
import com.systemvote.systemvoteback.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody @Validated LoginRequest request)
    {
        return new ResponseEntity<>("Token: " + authService.attemptLogin(request.getUsername(), request.getPassword()).getAccessToken(), HttpStatus.OK);
    }
}
