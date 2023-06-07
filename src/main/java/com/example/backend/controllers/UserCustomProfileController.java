package com.example.backend.controllers;

import com.example.backend.dto.MusicEntityTransferBody;
import com.example.backend.exceptions.NonexistentAlbum;
import com.example.backend.exceptions.NonexistentArtist;
import com.example.backend.exceptions.NonexistentTrack;
import com.example.backend.exceptions.NonexistentUser;
import com.example.backend.models.Album;
import com.example.backend.models.Artist;
import com.example.backend.models.Track;
import com.example.backend.services.MusicInfoForUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping("/api/user-custom-profile")
@CrossOrigin(origins = "http://localhost:3000")
final public class UserCustomProfileController {

    private final MusicInfoForUserService musicInfoForUsersService;

    public UserCustomProfileController(MusicInfoForUserService musicInfoForUsersService) {
        this.musicInfoForUsersService = musicInfoForUsersService;
    }

    @PostMapping("/add-favourite-album")
    public ResponseEntity<String> addFavouriteAlbum(@RequestBody MusicEntityTransferBody<Album> albumFavouriteBody) {
        try {
            musicInfoForUsersService.addFavouriteAlbum(albumFavouriteBody);
            return new ResponseEntity<>("Added successfully", HttpStatus.CREATED);
        } catch (NonexistentUser | NonexistentAlbum e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/add-favourite-artist")
    public ResponseEntity<String> addFavoriteArtist(@RequestBody MusicEntityTransferBody<Artist> artistFavouriteBody) {
        try {
            musicInfoForUsersService.addFavouriteArtist(artistFavouriteBody);
            return new ResponseEntity<>("Added successfully", HttpStatus.CREATED);
        } catch (NonexistentUser | NonexistentArtist e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/add-favourite-track")
    public ResponseEntity<String> addFavouriteTrack(@RequestBody MusicEntityTransferBody<Track> trackFavouriteBody) {
        try {
            musicInfoForUsersService.addFavouriteTrack(trackFavouriteBody);
            return new ResponseEntity<>("Added successfully", HttpStatus.CREATED);
        } catch (NonexistentUser | NonexistentTrack e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/remove-favourite-album")
    public ResponseEntity<String> removeFavouriteAlbum(@RequestBody MusicEntityTransferBody<Album> albumFavouriteBody) {
        try {
            musicInfoForUsersService.removeFavouriteAlbum(albumFavouriteBody);
            return new ResponseEntity<>("Removed successfully", HttpStatus.CREATED);
        } catch (NonexistentUser | NonexistentAlbum e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/remove-favourite-artist")
    public ResponseEntity<String> removeFavouriteArtist(@RequestBody MusicEntityTransferBody<Artist> artistFavouriteBody) {
        try {
            musicInfoForUsersService.removeFavouriteArtist(artistFavouriteBody);
            return new ResponseEntity<>("Removed successfully", HttpStatus.CREATED);
        } catch (NonexistentUser | NonexistentArtist e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/remove-favourite-track")
    public ResponseEntity<String> removeFavouriteTrack(@RequestBody MusicEntityTransferBody<Track> trackFavouriteBody) {
        try {
            musicInfoForUsersService.removeFavouriteTrack(trackFavouriteBody);
            return new ResponseEntity<>("Removed successfully", HttpStatus.NO_CONTENT);
        } catch (NonexistentUser | NonexistentTrack e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/add-recent-album")
    public ResponseEntity<String> addRecentAlbum(@RequestBody MusicEntityTransferBody<Album> albumRecentBody) {
        try {
            musicInfoForUsersService.addRecentAlbum(albumRecentBody);
            return new ResponseEntity<>("Added successfully", HttpStatus.NO_CONTENT);
        } catch (NonexistentUser | NonexistentAlbum e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/add-recent-artist")
    public ResponseEntity<String> addRecentArtist(@RequestBody MusicEntityTransferBody<Artist> artistRecentBody) {
        try {
            musicInfoForUsersService.addRecentArtist(artistRecentBody);
            return new ResponseEntity<>("Added successfully", HttpStatus.NO_CONTENT);
        } catch (NonexistentUser | NonexistentArtist e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/add-recent-track")
    public ResponseEntity<String> addRecentTrack(@RequestBody MusicEntityTransferBody<Track> trackRecentBody) {
        try {
            musicInfoForUsersService.addRecentTrack(trackRecentBody);
            return new ResponseEntity<>("Added successfully", HttpStatus.CREATED);
        } catch (NonexistentUser | NonexistentTrack e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);

        }
    }

    @GetMapping("/tracks-recommendations/{id}")
    public ResponseEntity<Set<Track>> getTracksRecommendations(@PathVariable UUID id) {
        try {
            return new ResponseEntity<>(musicInfoForUsersService.getTracksRecommendations(id), HttpStatus.OK);
        } catch (NonexistentUser e) {
            return new ResponseEntity<>(Set.of(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/albums-recommendations/{id}")
    public ResponseEntity<Set<Album>> getAlbumsRecommendations(@PathVariable UUID id) {
        try {
            return new ResponseEntity<>(musicInfoForUsersService.getAlbumsRecommendations(id), HttpStatus.OK);
        } catch (NonexistentUser e) {
            return new ResponseEntity<>(Set.of(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/artists-recommendations/{id}")
    public ResponseEntity<Set<Artist>> getArtistsRecommendations(@PathVariable UUID id) {
        try {
            return new ResponseEntity<>(musicInfoForUsersService.getArtistsRecommendations(id), HttpStatus.OK);
        } catch (NonexistentUser e) {
            return new ResponseEntity<>(Set.of(), HttpStatus.NOT_FOUND);
        }
    }
}
