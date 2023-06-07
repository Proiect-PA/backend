package com.example.backend.exceptions;

public class NonexistentArtist extends Exception {
    public NonexistentArtist() {
        super("Artist does not exist!");
    }
}
