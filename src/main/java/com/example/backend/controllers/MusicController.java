package com.example.backend.controllers;

import com.example.backend.dto.MusicEntityTransferBody;
import com.example.backend.dto.SearchFilter;
import com.example.backend.exceptions.NonexistentAlbum;
import com.example.backend.exceptions.NonexistentArtist;
import com.example.backend.exceptions.NonexistentUser;
import com.example.backend.models.Album;
import com.example.backend.models.Artist;
import com.example.backend.models.MusicEntity;
import com.example.backend.models.Track;
import com.example.backend.services.MusicService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Set;
import java.util.UUID;

@RestController
@CrossOrigin
@RequestMapping("/api/music")
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

    @PostMapping("/search")
    public ResponseEntity<Set<MusicEntity>> getSearchedMusicEntities(@RequestBody SearchFilter searchFilter){
        return new ResponseEntity<>(musicService.getSearchedMusicEntities(searchFilter), HttpStatus.OK);
    }

    @PostMapping("/is-loved-track")
    public ResponseEntity<Boolean> isLovedTrack(@RequestBody MusicEntityTransferBody<Track> track) {
        try {
            return new ResponseEntity<>(musicService.isLovedTrack(track), HttpStatus.OK);
        }   catch (NonexistentUser e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/is-loved-artist")
    public ResponseEntity<Boolean> isLovedArtist(@RequestBody MusicEntityTransferBody<Artist> artist) {
        try {
            return new ResponseEntity<>(musicService.isLovedArtist(artist), HttpStatus.OK);
        }   catch (NonexistentUser e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/is-loved-album")
    public ResponseEntity<Boolean> isLovedAlbum(@RequestBody MusicEntityTransferBody<Album> album) {
        try {
            return new ResponseEntity<>(musicService.isLovedAlbum(album), HttpStatus.OK);
        }   catch (NonexistentUser e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/random-playlist")
    public ResponseEntity<Set<Track>> getRandomPlaylist() {
        return new ResponseEntity<>(musicService.getRandomPlaylist(), HttpStatus.OK);
    }

    @GetMapping("/album/{id}/tracks")
    public ResponseEntity<Set<Track>> getTracksByAlbumId(@PathVariable UUID id) {
        try {
            return new ResponseEntity<>(musicService.getTrackByAlbumId(id), HttpStatus.OK);
        } catch (NonexistentAlbum e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/artist/{id}/albums")
    public ResponseEntity<Set<Album>> getAlbumsByArtistId(@PathVariable UUID id) {
        try {
            return new ResponseEntity<>(musicService.getAlbumByArtistId(id), HttpStatus.OK);
        } catch (NonexistentArtist e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
