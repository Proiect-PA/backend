package com.example.backend.services;

import com.example.backend.dto.UsernameIdBody;
import com.example.backend.models.User;
import com.example.backend.repositories.UsersRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UsersService {
    private final UsersRepository usersRepository;
    public User saveUser(User user) {
        usersRepository.save(user);
        return user;
    }

    public Optional<User> findByEmail(String email) {
        return usersRepository.findByEmail(email);
    }

    public Optional<User> findByUsername(String username) {
        return usersRepository.findByUsername(username);
    }

    public Optional<User> findById(UUID id) {
        return usersRepository.findById(id);
    }

    @Transactional
    public void changeUsernameById(UsernameIdBody usernameIdBody) {
        Optional<User> userToChange = findById(usernameIdBody.getId());
        if(userToChange.isPresent()) {
            usersRepository.updateUsername(usernameIdBody.getId(), usernameIdBody.getUsername());
        }
    }
}
