package com.example.backend.services;

import com.example.backend.models.Album;
import com.example.backend.models.Artist;
import com.example.backend.models.Track;
import com.example.backend.repositories.AlbumsRepository;
import com.example.backend.repositories.ArtistsRepository;
import com.example.backend.repositories.TracksRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MusicService {

    private final AlbumsRepository albumsRepository;
    private final ArtistsRepository artistsRepository;
    private final TracksRepository tracksRepository;


    public MusicService(AlbumsRepository albumsRepository, ArtistsRepository artistsRepository, TracksRepository tracksRepository) {
        this.albumsRepository = albumsRepository;
        this.artistsRepository = artistsRepository;
        this.tracksRepository = tracksRepository;
    }

    public List<Album> getAllAlbums() {
        return albumsRepository.findAll();
    }

    public List<Track> getAllTracks() {
        return tracksRepository.findAll();
    }

    public List<Artist> getAllArtists() {
        return artistsRepository.findAll();
    }
}
