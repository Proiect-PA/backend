package com.example.backend.exceptions;

public class NonexistentUser extends Exception {
    public NonexistentUser() {
        super("User does not exist");
    }
}
