package com.example.backend.repositories;

import com.example.backend.models.Artist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ArtistsRepository extends JpaRepository<Artist, UUID> {
    Optional<Artist> findByName(String name);
}
