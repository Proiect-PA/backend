package com.example.backend.dto;

import java.util.UUID;

public class UsernameIdBody {
    private String username;
    private UUID id;

    public UsernameIdBody() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}
