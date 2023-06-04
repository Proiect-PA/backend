package com.example.backend.dto;

import lombok.Data;


@Data
public class RegisterRequestBody {
    private String email;

    private String username;
    private String password;
    private String firstname;
    private String lastname;
}
