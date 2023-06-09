package com.example.backend.services;

import com.example.backend.dto.MusicEntityTransferBody;
import com.example.backend.dto.SearchFilter;
import com.example.backend.exceptions.NonexistentAlbum;
import com.example.backend.exceptions.NonexistentArtist;
import com.example.backend.exceptions.NonexistentUser;
import com.example.backend.models.*;
import com.example.backend.repositories.AlbumsRepository;
import com.example.backend.repositories.ArtistsRepository;
import com.example.backend.repositories.TracksRepository;
import com.example.backend.repositories.UsersRepository;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.*;

@Service
public class MusicService {

    private final AlbumsRepository albumsRepository;
    private final ArtistsRepository artistsRepository;
    private final TracksRepository tracksRepository;
    private final UsersRepository usersRepository;
    private final UsersService usersService;

    public MusicService(AlbumsRepository albumsRepository, ArtistsRepository artistsRepository, TracksRepository tracksRepository, UsersRepository usersRepository, UsersService usersService) {
        this.albumsRepository = albumsRepository;
        this.artistsRepository = artistsRepository;
        this.tracksRepository = tracksRepository;
        this.usersRepository = usersRepository;
        this.usersService = usersService;
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
        return new HashSet<>(Objects.requireNonNull(user.map(MusicInfoForUser::getFavouriteTracks).orElse(null)));
    }

    public Set<Album> getFavouriteAlbumsByUserId(UUID id) {
        Optional<User> user = usersRepository.findById(id);
        return new HashSet<>(Objects.requireNonNull(user.map(MusicInfoForUser::getFavouriteAlbums).orElse(null)));
    }

    public Set<Artist> getFavouriteArtistsByUserId(UUID id) {
        Optional<User> user = usersRepository.findById(id);
        return new HashSet<>(Objects.requireNonNull(user.map(MusicInfoForUser::getFavouriteArtists).orElse(null)));
    }

    public Set<MusicEntity> getSearchedMusicEntities(SearchFilter searchFilter) {
        Set<MusicEntity> searchedFor = new HashSet<>();

        Optional<Album> foundAlbum = albumsRepository.findByTitle(searchFilter.getSearchInput());
        foundAlbum.ifPresent(searchedFor::add);
        Optional<Artist> foundArtist = artistsRepository.findByName(searchFilter.getSearchInput());
        foundArtist.ifPresent(searchedFor::add);
        Optional<Track> foundTrack = tracksRepository.findByName(searchFilter.getSearchInput());
        foundTrack.ifPresent(searchedFor::add);
        return searchedFor;
    }

    public Optional<Track> getTrackById(UUID id) {
        return tracksRepository.findById(id);
    }

    public Optional<Album> getAlbumById(UUID id) {
        return albumsRepository.findById(id);
    }


    public boolean isLovedTrack(MusicEntityTransferBody<Track> track) throws NonexistentUser {
        try {
            User user = usersService.findById(track.getUserId());
            for(Track t : user.getFavouriteTracks()) {
                if(t.getId().equals(track.getEntity().getId())) {
                    return true;
                }
            }
            return false;
        } catch (NonexistentUser e) {
            throw new NonexistentUser();
        }
    }

    public boolean isLovedAlbum(MusicEntityTransferBody<Album> album) throws NonexistentUser {
        try {
            User user = usersService.findById(album.getUserId());
            for(Album a : user.getFavouriteAlbums()) {
                if(a.getId().equals(album.getEntity().getId())) {
                    return true;
                }
            }
            return false;
        } catch (NonexistentUser e) {
            throw new NonexistentUser();
        }
    }

    public boolean isLovedArtist(MusicEntityTransferBody<Artist> artist) throws NonexistentUser {
        try {
            User user = usersService.findById(artist.getUserId());
            for(Artist a : user.getFavouriteArtists()) {
                if(a.getId().equals(artist.getEntity().getId())) {
                    return true;
                }
            }
            return false;
        } catch (NonexistentUser e) {
            throw new NonexistentUser();
        }
    }


    public Set<Track> getRandomPlaylist() {
        Set<Track> playlist = new HashSet<>();
        Random random = new Random();
        int size = random.nextInt(25);
        int sizeOfTracks = tracksRepository.countAll();
        for(int i = 0; i < size; i++) {
            int index = random.nextInt(sizeOfTracks);
            playlist.add(tracksRepository.findAll().get(index));
        }

        return playlist;
    }


    public Set<Track> getTrackByAlbumId(UUID id) throws NonexistentAlbum {
        Optional<List<Track>> tracks = tracksRepository.getTrackByAlbumId(id);
       if(tracks.isPresent()) {
           return new HashSet<>(tracks.get());
       } else {
           throw new NonexistentAlbum();
       }
    }

    public Set<Album> getAlbumByArtistId(UUID id) throws NonexistentArtist {
        Optional<List<Album>> albums = albumsRepository.getAlbumByArtistId(id);
        if(albums.isPresent()) {
            return new HashSet<>(albums.get());
        } else {
            throw new NonexistentArtist();
        }
    }
}
