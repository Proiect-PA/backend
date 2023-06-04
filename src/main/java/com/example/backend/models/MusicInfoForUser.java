package com.example.backend.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@MappedSuperclass
@Data
public class MusicInfoForUser {
    @Transient
    private Map<MusicEntity, Integer> frequencies;

    @ManyToMany
    @JoinTable(name = "favourite_tracks",
    joinColumns = @JoinColumn(name = "track_id"),
    inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<Track> favouriteTracks;

    @ManyToMany
    @JoinTable(name = "favourite_albums",
    joinColumns = @JoinColumn(name = "album_id"),
    inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<Album> favouriteAlbums;

    @ManyToMany
    @JoinTable(name = "favourite_artists",
    joinColumns = @JoinColumn(name = "artist_id"),
    inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<Artist> favouriteArtists;

    @ManyToMany
    @JoinTable(name = "last_played_tracks",
    joinColumns = @JoinColumn(name = "track_id"),
    inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<Track> lastPlayedTracks;

    @ManyToMany
    @JoinTable(name = "last_played_albums",
    joinColumns = @JoinColumn(name = "album_id"),
    inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<Album> lastPlayedAlbums;

    @ManyToMany
    @JoinTable(name = "last_played_artists",
    joinColumns = @JoinColumn(name = "artist_id"),
    inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<Artist> lastPlayedArtists;

    public MusicInfoForUser() {
        favouriteAlbums = new ArrayList<>(100);
        favouriteArtists = new ArrayList<>(100);
        favouriteTracks = new ArrayList<>(100);
        lastPlayedAlbums = new ArrayList<>(100);
        lastPlayedArtists = new ArrayList<>(100);
        lastPlayedTracks = new ArrayList<>(100);
        frequencies = new HashMap<>(200);
    }

    public void checkChangeFavouriteTrack(MusicEntity recentTrack) {
        for(MusicEntity track : frequencies.keySet()) {
            if(frequencies.get(track) < frequencies.get(recentTrack)) {
                removeFavouriteTrack((Track) track);
                addFavouriteTrack((Track) recentTrack);
                lastPlayedTracks.remove(recentTrack);
                removeFrequency(track);
            }
        }
    }

    public void checkChangeFavouriteAlbum(MusicEntity recentAlbum) {
        for(MusicEntity album : frequencies.keySet()) {
            if(frequencies.get(album) < frequencies.get(recentAlbum)) {
                removeFavouriteAlbum((Album) album);
                addFavouriteAlbum((Album) recentAlbum);
                lastPlayedAlbums.remove(recentAlbum);
                removeFrequency(album);
            }
        }
    }

    public void checkChangeFavouriteArtist(MusicEntity recentArtist) {
        for(MusicEntity artist : frequencies.keySet()) {
            if(frequencies.get(artist) < frequencies.get(recentArtist)) {
                removeFavouriteArtist((Artist) artist);
                addFavouriteArtist((Artist) recentArtist);
                lastPlayedArtists.remove(recentArtist);
                removeFrequency(artist);
            }
        }
    }

    public void addFavouriteTrack(Track track) {
        this.favouriteTracks.add(track);
        addFrequency(track);
    }

    public void addFavouriteAlbum(Album album) {
        this.favouriteAlbums.add(album);
        addFrequency(album);
    }
    public void addFavouriteArtist(Artist artist) {
        this.favouriteArtists.add(artist);
        addFrequency(artist);
    }

    public void addLastPlayedTrack(Track track) {
        this.lastPlayedTracks.add(track);
        addFrequency(track);
    }

    public void addLastPlayedAlbum(Album album) {
        this.lastPlayedAlbums.add(album);
        addFrequency(album);
    }

    public void addLastPlayedArtist(Artist artist) {
        this.lastPlayedArtists.add(artist);
        addFrequency(artist);
    }

    public void removeFavouriteTrack(Track track) {
        this.favouriteTracks.remove(track);
        removeFrequency(track);
    }

    public void removeFavouriteAlbum(Album album) {
        this.favouriteAlbums.remove(album);
        removeFrequency(album);
    }

    public void removeFavouriteArtist(Artist artist) {
        this.favouriteArtists.remove(artist);
        removeFrequency(artist);
    }


    private void addFrequency(MusicEntity musicEntity) {
        if(frequencies.containsKey(musicEntity)) {
            frequencies.put(musicEntity, frequencies.get(musicEntity) + 1);
        } else {
            frequencies.put(musicEntity, 1);
        }
    }

    private void removeFrequency(MusicEntity musicEntity) {
        frequencies.remove(musicEntity);
    }
}
