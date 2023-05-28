package com.example.backend.utils;

import com.example.backend.models.Album;
import com.example.backend.models.Artist;
import com.example.backend.models.Genre;
import com.example.backend.models.Track;
import com.example.backend.repositories.*;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.parseInt;


@Service
public class CsvParser {
    private final UsersRepository usersRepository;
    private final AlbumsRepository albumsRepository;
    private final ArtistsRepository artistsRepository;
    private final GenresRepository genresRepository;

    private final TracksRepository tracksRepository;

    public CsvParser(UsersRepository usersRepository, AlbumsRepository albumsRepository, ArtistsRepository artistsRepository, GenresRepository genresRepository, TracksRepository tracksRepository) {
        this.usersRepository = usersRepository;
        this.albumsRepository = albumsRepository;
        this.artistsRepository = artistsRepository;
        this.genresRepository = genresRepository;
        this.tracksRepository = tracksRepository;
    }

    public void addTrackInDB(String[] trackData) {
        Artist artist = new Artist(trackData[3]);
        try {
            artistsRepository.save(artist);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        List<Genre> genres = new ArrayList<>();
        genres.add(new Genre(trackData[4]));
        try {
            genresRepository.saveAll(genres);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        Album album = new Album(parseInt(trackData[2]), trackData[1], artist, genres);
        try {
            albumsRepository.save(album);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        Track track = new Track(trackData[0], album);
        try {
            tracksRepository.save(track);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void parseTracks(String fileContent) throws IOException, CsvException {

        File file = new File("./data/csv_tracks.csv");
        try (OutputStream outStream = new FileOutputStream(file)) {
            outStream.write(fileContent.getBytes());
        }

        try (CSVReader reader = new CSVReader(new FileReader(file))) {
            List<String[]> res = reader.readAll();
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

}
