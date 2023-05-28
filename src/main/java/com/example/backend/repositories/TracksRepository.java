package com.example.backend.repositories;

import com.example.backend.models.Track;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface TracksRepository extends JpaRepository<Track, UUID> {
    Optional<Track> findByName(String name);
}
