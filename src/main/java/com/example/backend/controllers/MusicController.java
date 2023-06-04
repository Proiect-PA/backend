package com.example.backend.controllers;

import com.example.backend.models.Album;
import com.example.backend.models.Artist;
import com.example.backend.models.Track;
import com.example.backend.services.MusicService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping("/api/music")
@CrossOrigin(origins = "http://localhost:3000")
public class MusicController {

    private final MusicService musicService;

    public MusicController(MusicService musicService) {
        this.musicService = musicService;
    }

    @GetMapping("/albums/all")
    public ResponseEntity<Set<Album>> getAllAlbums() {
        return new ResponseEntity<>(musicService.getAllAlbums(), HttpStatus.OK);
    }

    @GetMapping("/artists/all")
    public ResponseEntity<Set<Artist>> getAllArtists() {
        return new ResponseEntity<>(musicService.getAllArtists(), HttpStatus.OK);
    }

    @GetMapping("/tracks/all")
    public ResponseEntity<Set<Track>> getAllTracks() {
        return new ResponseEntity<>(musicService.getAllTracks(), HttpStatus.OK);
    }


    @GetMapping("/user/{id}/tracks")
    public ResponseEntity<Set<Track>> getFavouriteTracksByUserId(@PathVariable UUID id) {
        Set<Track> tracks = musicService.getFavouriteTracksByUserId(id);
        tracks.forEach(System.out::println);
        return new ResponseEntity<>(musicService.getFavouriteTracksByUserId(id), HttpStatus.OK);
    }

    @GetMapping("/user/{id}/artists")
    public ResponseEntity<Set<Artist>> getFavouriteArtistsByUserId(@PathVariable UUID id) {
        return new ResponseEntity<>(musicService.getFavouriteArtistsByUserId(id), HttpStatus.OK);
    }

    @GetMapping("/user/{id}/albums")
    public ResponseEntity<Set<Album>> getFavouriteAlbumsByUserId(@PathVariable UUID id) {
        return new ResponseEntity<>(musicService.getFavouriteAlbumsByUserId(id), HttpStatus.OK);
    }
}
