package com.example.backend.utils;

import com.example.backend.models.*;
import com.example.backend.repositories.AlbumsRepository;

import com.example.backend.repositories.TracksRepository;

import java.util.*;

public class Recommendation {

    private final TracksRepository tracksRepository;
    private final AlbumsRepository albumsRepository;

    List<Track> userTrackList;
    List<Album> userAlbumList;
    List<Artist> userArtistList;

    public Recommendation(User user, TracksRepository tracksRepository, AlbumsRepository albumsRepository) {
        this.tracksRepository = tracksRepository;
        this.albumsRepository = albumsRepository;

        userTrackList = new ArrayList<>();
        userTrackList.addAll(user.getFavouriteTracks());
        userTrackList.addAll(user.getLastPlayedTracks());

        userAlbumList = new ArrayList<>();
        userAlbumList.addAll(user.getFavouriteAlbums());
        userAlbumList.addAll(user.getLastPlayedAlbums());

        userArtistList = new ArrayList<>();
        userArtistList.addAll(user.getFavouriteArtists());
        userArtistList.addAll(user.getLastPlayedArtists());
    }

    public Set<Track> getRecommendedTracks() {
        Set<Track> recommendedTracks = new HashSet<>();
        userTrackList
                .forEach(track -> {
                    Optional<List<Track>> tracksByAlbum = tracksRepository.findByAlbumId(track.getAlbum().getId());
                    tracksByAlbum.ifPresent(recommendedTracks::addAll);

                    Optional<List<Track>> tracksByArtist = tracksRepository.findByArtistId(track.getAlbum().getArtist().getId());
                    tracksByArtist.ifPresent(recommendedTracks::addAll);
                });
        return recommendedTracks;
    }

    public Set<Album> getRecommendedAlbums() {
        Set<Album> recommendedAlbums = new HashSet<>();
        userAlbumList
                .forEach(album -> {
                    Optional<List<Album>> albumsByArtist = albumsRepository.findByArtistId(album.getArtist().getId());
                    albumsByArtist.ifPresent(recommendedAlbums::addAll);
                });

        return recommendedAlbums;
    }

    public Set<Artist> getRecommendedArtists() {
        Set<Artist> recommendedArtists = new HashSet<>();
        List<Genre> allGenresForUser = new ArrayList<>();
        userArtistList
                .forEach(artist ->
                        albumsRepository.findByArtistId(artist.getId())
                                .ifPresent(albums ->
                                        albums.forEach(album -> allGenresForUser.add(album.getGenre())))
                );

        List<Album> albumsByGenre = new ArrayList<>();

        allGenresForUser
                .forEach(genre ->
                        albumsRepository.findByGenreId(genre.getId())
                                .ifPresent(albumsByGenre::addAll)
                );

        albumsByGenre
                .forEach(album ->
                        recommendedArtists.add(album.getArtist())
                );

        return recommendedArtists;
    }
}
