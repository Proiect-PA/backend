package com.example.backend.services;

import com.example.backend.dto.LoginRequestBody;
import com.example.backend.dto.RegisterRequestBody;
import com.example.backend.exceptions.AlreadyExistentUser;
import com.example.backend.exceptions.NonexistentUser;
import com.example.backend.models.User;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UsersService usersService;

    public AuthService(UsersService usersService) {
        this.usersService = usersService;
    }

    public void login(LoginRequestBody loginDetails) throws NonexistentUser {
        try {
            usersService.findByEmail(loginDetails.getEmail());
        } catch (NonexistentUser e) {
            throw new NonexistentUser();
        }
    }

    public void register(RegisterRequestBody registerRequestBody) throws AlreadyExistentUser {
        try {
            usersService.findByEmail(registerRequestBody.getEmail());
            throw new AlreadyExistentUser();
        } catch (NonexistentUser e) {
            usersService.saveUser(registerRequestBody);
        }
    }
}
