package com.example.backend.exceptions;

public class NonexistentAlbum extends Exception {
    public NonexistentAlbum() {
        super("Album does not exist!");
    }
}
