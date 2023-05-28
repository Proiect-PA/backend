package com.example.backend.repositories;

import com.example.backend.models.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface GenresRepository extends JpaRepository<Genre, UUID> {
    Optional<Genre> findByName(String name);
}
