package com.example.backend.repositories;

import com.example.backend.models.Album;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AlbumsRepository extends JpaRepository<Album, UUID> {
    Optional<Album> findByTitle(String title);


}
