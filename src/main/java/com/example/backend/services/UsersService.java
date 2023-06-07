package com.example.backend.services;

import com.example.backend.dto.RegisterRequestBody;
import com.example.backend.dto.UsernameIdBody;
import com.example.backend.exceptions.NonexistentUser;
import com.example.backend.models.User;
import com.example.backend.repositories.*;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UsersService {

    private final UsersRepository usersRepository;

    public UsersService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public void saveUser(RegisterRequestBody registerRequestBody) {
        User user = new User(registerRequestBody);
        usersRepository.save(user);
    }

    public User findByEmail(String email) throws NonexistentUser {
        Optional<User> user = usersRepository.findByEmail(email);
        if(user.isPresent()) {
            return user.get();
        } else {
            throw new NonexistentUser();
        }
    }

    public User findById(UUID id) throws NonexistentUser {
        Optional<User> user = usersRepository.findById(id);
        if(user.isPresent()) {
            return user.get();
        } else {
            throw new NonexistentUser();
        }
    }

    @Transactional
    public void changeUsernameById(UsernameIdBody usernameIdBody) {
        usersRepository.updateUsername(usernameIdBody.getId(), usernameIdBody.getUsername());
    }
}
