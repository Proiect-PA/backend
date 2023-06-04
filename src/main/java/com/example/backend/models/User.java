package com.example.backend.models;

import com.example.backend.dto.RegisterRequestBody;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User extends MusicInfoForUser {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(unique = true)
    private String email;

    @Column(unique = true)
    private String username;
    private String password;
    private String firstname;
    private String lastname;

    public User(RegisterRequestBody registerRequestBody) {
        this.email = registerRequestBody.getEmail();
        this.username = registerRequestBody.getUsername();
        this.password = registerRequestBody.getPassword();
        this.firstname = registerRequestBody.getFirstname();
        this.lastname = registerRequestBody.getLastname();
    }
}
