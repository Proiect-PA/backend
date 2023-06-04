package com.example.backend.services;

import com.example.backend.dto.RegisterRequestBody;
import com.example.backend.dto.UsernameIdBody;
import com.example.backend.models.User;
import com.example.backend.repositories.AlbumsRepository;
import com.example.backend.repositories.ArtistsRepository;
import com.example.backend.repositories.TracksRepository;
import com.example.backend.repositories.UsersRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UsersService extends MusicInfoForUserService {

    private final UsersRepository usersRepository;

    public UsersService(UsersRepository usersRepository, AlbumsRepository albumsRepository, ArtistsRepository artistsRepository, TracksRepository tracksRepository, UsersRepository usersRepository1) {
        super(usersRepository, albumsRepository, artistsRepository, tracksRepository);
        this.usersRepository = usersRepository1;
    }

    public void saveUser(RegisterRequestBody registerRequestBody) {
        User user = new User(registerRequestBody);
        usersRepository.save(user);
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
