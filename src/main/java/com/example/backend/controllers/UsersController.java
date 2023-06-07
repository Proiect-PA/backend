package com.example.backend.controllers;

import com.example.backend.dto.UsernameIdBody;
import com.example.backend.exceptions.NonexistentUser;
import com.example.backend.models.User;
import com.example.backend.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@CrossOrigin
@RequestMapping("/api/users")
public class UsersController {
    private final UsersService usersService;

    @Autowired
    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    @GetMapping("id={id}")
    public ResponseEntity<User> getUserById(@PathVariable UUID id) {
        try {
            User user = usersService.findById(id);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (NonexistentUser e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("email={email}")
    public ResponseEntity<User> getUserByEmail(@PathVariable String email) {
        try {
            User user = usersService.findByEmail(email);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (NonexistentUser e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping("id={id}")
    public ResponseEntity<User> changeUserById(@PathVariable UUID id, @RequestBody UsernameIdBody usernameIdBody) {
        usernameIdBody.setId(id);
        usersService.changeUsernameById(usernameIdBody);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
