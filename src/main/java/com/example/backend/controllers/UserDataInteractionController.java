package com.example.backend.controllers;

import com.example.backend.dto.MusicEntityTransferBody;
import com.example.backend.models.Album;
import com.example.backend.models.Artist;
import com.example.backend.models.Track;
import com.example.backend.services.UsersService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class UserDataInteractionController {

    private final UsersService usersService;

    public UserDataInteractionController(UsersService usersService) {
        this.usersService = usersService;
    }

    @PostMapping("/add-favourite-album")
    public ResponseEntity<Void> addFavouriteAlbum(@RequestBody MusicEntityTransferBody<Album> albumFavouriteBody) {
        usersService.addFavouriteAlbum(albumFavouriteBody);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/add-favourite-artist")
    public ResponseEntity<Void> addFavoriteArtist(@RequestBody MusicEntityTransferBody<Artist> artistFavouriteBody) {
        usersService.addFavouriteArtist(artistFavouriteBody);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/add-favourite-track")
    public ResponseEntity<Void> addFavouriteTrack(@RequestBody MusicEntityTransferBody<Track> trackFavouriteBody) {
        usersService.addFavouriteTrack(trackFavouriteBody);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/remove-favourite-album")
    public ResponseEntity<Void> removeFavouriteAlbum(@RequestBody MusicEntityTransferBody<Album> albumFavouriteBody) {
        usersService.removeFavouriteAlbum(albumFavouriteBody);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/remove-favourite-artist")
    public ResponseEntity<Void> removeFavouriteArtist(@RequestBody MusicEntityTransferBody<Artist> artistFavouriteBody) {
        usersService.removeFavouriteArtist(artistFavouriteBody);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/remove-favourite-track")
    public ResponseEntity<Void> removeFavouriteTrack(@RequestBody MusicEntityTransferBody<Track> trackFavouriteBody) {
        usersService.removeFavouriteTrack(trackFavouriteBody);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/add-recent-album")
    public ResponseEntity<Void> addRecentAlbum(@RequestBody MusicEntityTransferBody<Album> albumRecentBody) {
        usersService.addRecentAlbum(albumRecentBody);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/add-recent-artist")
    public ResponseEntity<Void> addRecentArtist(@RequestBody MusicEntityTransferBody<Artist> artistRecentBody) {
        usersService.addRecentArtist(artistRecentBody);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/add-recent-track")
    public ResponseEntity<Void> addRecentTrack(@RequestBody MusicEntityTransferBody<Track> trackRecentBody) {
        usersService.addRecentTrack(trackRecentBody);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/tracks-recommendations/{id}")
    public ResponseEntity<Set<Track>> getTracksRecommendations(@PathVariable UUID id) {
        return ResponseEntity.ok(usersService.getTracksRecommendations(id));
    }

    @GetMapping("/albums-recommendations/{id}")
    public ResponseEntity<Set<Album>> getAlbumsRecommendations(@PathVariable UUID id) {
        return ResponseEntity.ok(usersService.getAlbumsRecommendations(id));
    }
}
