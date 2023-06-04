package com.example.backend.utils;

import com.example.backend.models.Album;
import com.example.backend.models.Artist;
import com.example.backend.models.Genre;
import com.example.backend.models.Track;
import com.example.backend.repositories.*;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.parseInt;

@Service
public class DataSaving {

    private final AlbumsRepository albumsRepository;
    private final ArtistsRepository artistsRepository;
    private final GenresRepository genresRepository;

    private final TracksRepository tracksRepository;

    public DataSaving(AlbumsRepository albumsRepository, ArtistsRepository artistsRepository, GenresRepository genresRepository, TracksRepository tracksRepository) {
        this.albumsRepository = albumsRepository;
        this.artistsRepository = artistsRepository;
        this.genresRepository = genresRepository;
        this.tracksRepository = tracksRepository;
    }

    @Transactional
    public void saveArtist(Artist artist) {
        if (artistsRepository.findByName(artist.getName()).isEmpty()) {
            artistsRepository.saveAndFlush(artist);
        }
    }

    @Transactional
    public void saveGenres(List<Genre> genres) {
        for (Genre genre : genres) {
            genre.setName(genre.getName().trim());
            if (genresRepository.findByName(genre.getName()).isEmpty()) {
                genresRepository.saveAndFlush(genre);
            }
        }
    }

    @Transactional
    public void saveAlbum(Album album) {
        if (albumsRepository.findByTitle(album.getTitle()).isEmpty()) {
            albumsRepository.saveAndFlush(album);
        }
    }

    @Transactional
    public void saveTrack(Track track) {
        if (tracksRepository.findByName(track.getName()).isEmpty()) {
            tracksRepository.saveAndFlush(track);
        }
    }

    public void addTrackInDB(String[] trackData) {
        try {
            Artist artist = new Artist(trackData[3]);
            saveArtist(artist);

            List<Genre> genres = new ArrayList<>();
            genres.add(new Genre(trackData[4]));
            saveGenres(genres);

            List<Genre> newGenres = List.of(genresRepository.findByName(trackData[4]).get());
            Artist newArtist = artistsRepository.findByName(trackData[3]).get();

            Album album = new Album(parseInt(trackData[2]), trackData[1], newArtist, newGenres);
            saveAlbum(album);

            Album newAlbum = albumsRepository.findByTitle(trackData[1]).get();
            Track track = new Track(trackData[0], newAlbum);
            saveTrack(track);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void saveTracks(List<String[]> res) {
        int lineCount = 0;
        for (String[] trackData : res) {
            if (lineCount == 0) {
                lineCount++;
                continue;
            }

            addTrackInDB(trackData);
        }
    }

}
