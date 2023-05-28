package com.example.backend.controllers;

import com.example.backend.dto.LoginRequestBody;
import com.example.backend.models.User;
import com.example.backend.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/auth")
public class AuthController {
    private final UsersService usersService;

    @Autowired
    public AuthController(UsersService usersService) {
        this.usersService = usersService;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequestBody loginDetails) {

        if(usersService.findByEmail(loginDetails.getEmail()).isPresent()) {
            return new ResponseEntity<>("ok", HttpStatus.OK);

        } else {
            return new ResponseEntity<>("User doesn't exist", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody User user) {
        if(usersService.findById(user.getId()).isPresent()) {
            return new ResponseEntity<>("User already exists", HttpStatus.OK);
        } else {
            usersService.saveUser(user);
            return new ResponseEntity<>("Registered", HttpStatus.CREATED);
        }
    }


}
