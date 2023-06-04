package com.example.backend.models;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "artists")
public class Artist implements MusicEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(unique=true)
    private String name;

    public Artist() {

    }

    public Artist(String name) {
        this.name = name;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
