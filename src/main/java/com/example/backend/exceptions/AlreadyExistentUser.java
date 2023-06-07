package com.example.backend.exceptions;

public class AlreadyExistentUser extends Exception {
    public AlreadyExistentUser() {
        super("User already exists");
    }
}
