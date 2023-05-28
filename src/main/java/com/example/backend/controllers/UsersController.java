package com.example.backend.controllers;

import com.example.backend.dto.UsernameIdBody;
import com.example.backend.models.User;
import com.example.backend.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/users")
public class UsersController {
    private final UsersService usersService;

    @Autowired
    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    @GetMapping("id={id}")
    public ResponseEntity<User> getUserById(@PathVariable UUID id) {
        Optional<User> user = usersService.findById(id);
        if (user.isPresent()) {
            return new ResponseEntity<>(user.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("email={email}")
    public ResponseEntity<User> getUserByEmail(@PathVariable String email) {
        Optional<User> user = usersService.findByEmail(email);
        if (user.isPresent()) {
            return new ResponseEntity<>(user.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PatchMapping("id={id}")
    public ResponseEntity<User> changeUserById(@PathVariable UUID id, @RequestBody UsernameIdBody usernameIdBody) {
        usernameIdBody.setId(id);
        usersService.changeUsernameById(usernameIdBody);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
