package com.example.backend.controllers;

import com.example.backend.dto.LoginRequestBody;
import com.example.backend.dto.RegisterRequestBody;
import com.example.backend.exceptions.AlreadyExistentUser;
import com.example.backend.exceptions.NonexistentUser;
import com.example.backend.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;
    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequestBody loginDetails) {
        try {
            authService.login(loginDetails);
            return new ResponseEntity<>("Logged in", HttpStatus.OK);
        } catch (NonexistentUser e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequestBody registerRequestBody) {
        try {
            authService.register(registerRequestBody);
            return new ResponseEntity<>("Registered", HttpStatus.OK);
        } catch (AlreadyExistentUser e) {
            return new ResponseEntity<>("User already exists", HttpStatus.OK);
        }
    }
}
