package com.example.backend.controllers;

import com.example.backend.models.Album;
import com.example.backend.models.Artist;
import com.example.backend.models.Track;
import com.example.backend.services.MusicService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/music")
@CrossOrigin(origins = "http://localhost:3000")
public class MusicController {

    private final MusicService musicService;

    public MusicController(MusicService musicService) {
        this.musicService = musicService;
    }

    @GetMapping("/albums/all")
    public ResponseEntity<List<Album>> getAllAlbums() {
        return new ResponseEntity<>(musicService.getAllAlbums(), HttpStatus.OK);
    }

    @GetMapping("/artists/all")
    public ResponseEntity<List<Artist>> getAllArtists() {
        return new ResponseEntity<>(musicService.getAllArtists(), HttpStatus.OK);
    }

    @GetMapping("/tracks/all")
    public ResponseEntity<List<Track>> getAllTracks() {
        return new ResponseEntity<>(musicService.getAllTracks(), HttpStatus.OK);
    }


}
