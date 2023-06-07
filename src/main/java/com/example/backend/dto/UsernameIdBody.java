package com.example.backend.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class UsernameIdBody {
    private String username;
    private UUID id;
}
