package com.example.backend.repositories;

import com.example.backend.models.Artist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ArtistsRepository extends JpaRepository<Artist, UUID> {
    @Query("select a from Artist a where lower(a.name) = lower(?1)")
    Optional<Artist> findByName(String name);
}
