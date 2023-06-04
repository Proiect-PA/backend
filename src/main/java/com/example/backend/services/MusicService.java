package com.example.backend.services;

import com.example.backend.models.*;
import com.example.backend.repositories.AlbumsRepository;
import com.example.backend.repositories.ArtistsRepository;
import com.example.backend.repositories.TracksRepository;
import com.example.backend.repositories.UsersRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Service
public class MusicService {

    private final AlbumsRepository albumsRepository;
    private final ArtistsRepository artistsRepository;
    private final TracksRepository tracksRepository;
    private final UsersRepository usersRepository;

    public MusicService(AlbumsRepository albumsRepository, ArtistsRepository artistsRepository, TracksRepository tracksRepository, UsersRepository usersRepository) {
        this.albumsRepository = albumsRepository;
        this.artistsRepository = artistsRepository;
        this.tracksRepository = tracksRepository;
        this.usersRepository = usersRepository;
    }

    public Set<Album> getAllAlbums() {

        return new HashSet<>(albumsRepository.findAll());
    }

    public Set<Track> getAllTracks() {
        return new HashSet<>(tracksRepository.findAll());
    }

    public Set<Artist> getAllArtists() {
        return new HashSet<>(artistsRepository.findAll());
    }

    public Set<Track> getFavouriteTracksByUserId(UUID id) {
        Optional<User> user = usersRepository.findById(id);
        return new HashSet<>(user.map(MusicInfoForUser::getFavouriteTracks).orElse(null));
    }

    public Set<Album> getFavouriteAlbumsByUserId(UUID id) {
        Optional<User> user = usersRepository.findById(id);
        return new HashSet<>(user.map(MusicInfoForUser::getFavouriteAlbums).orElse(null));
    }

    public Set<Artist> getFavouriteArtistsByUserId(UUID id) {
        Optional<User> user = usersRepository.findById(id);
        return new HashSet<>(user.map(MusicInfoForUser::getFavouriteArtists).orElse(null));
    }

    public Optional<Track> getTrackById(UUID id) {
        return tracksRepository.findById(id);
    }

    public Optional<Album> getAlbumById(UUID id) {
        return albumsRepository.findById(id);
    }

}
