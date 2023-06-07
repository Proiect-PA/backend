package com.example.backend.models;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "albums")
public class Album implements MusicEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private int releaseYear;
    @Column(unique=true)
    private String title;

    @ManyToOne
    @JoinColumn(name = "artist_id")
    private Artist artist;

    @ManyToOne
    @JoinColumn(name = "genre_id")
    private Genre genre;

    public Album() {

    }

    public Album(int releaseYear, String title, Artist artist, Genre genre) {
        this.releaseYear = releaseYear;
        this.title = title;
        this.artist = artist;
        this.genre = genre;
    }

    public Album(UUID id, int releaseYear, String title, Artist artist, Genre genre) {
        this.id = id;
        this.releaseYear = releaseYear;
        this.title = title;
        this.artist = artist;
        this.genre = genre;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return 0;
    }

    @Override
    public String toString() {
        return "Album{" +
                "releaseYear=" + releaseYear +
                ", title='" + title + '\'' +
                ", artist=" + artist +
                ", genre=" + genre +
                '}';
    }
}
