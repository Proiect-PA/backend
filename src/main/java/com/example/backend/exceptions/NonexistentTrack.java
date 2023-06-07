package com.example.backend.exceptions;

public class NonexistentTrack extends Exception {
    public NonexistentTrack() {
        super("Track does not exist!");
    }
}
