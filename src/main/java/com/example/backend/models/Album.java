package com.example.backend.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "albums")
public class Album {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private int releaseYear;
    @Column(unique=true)
    private String title;

    @ManyToOne
    @JoinColumn(name = "artist_id")
    private Artist artist;

    @ManyToMany
    @JoinTable(name = "album_genres",
            joinColumns = @JoinColumn(name = "album_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id"))
    private List<Genre> genres;

    public Album() {

    }

    public Album(int releaseYear, String title, Artist artist, List<Genre> genres) {
        this.releaseYear = releaseYear;
        this.title = title;
        this.artist = artist;
        this.genres = genres;
    }

    public Album(int id, int releaseYear, String title, Artist artist, List<Genre> genres) {
        this.id = id;
        this.releaseYear = releaseYear;
        this.title = title;
        this.artist = artist;
        this.genres = genres;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
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

    public void addGenre(Genre genre) {
        this.genres.add(genre);
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
                ", genres=" + genres +
                '}';
    }
}
