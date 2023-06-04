package com.example.backend.repositories;

import com.example.backend.models.Album;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface AlbumsRepository extends JpaRepository<Album, UUID> {
    Optional<Album> findByTitle(String title);

    @Query("SELECT a FROM Album a WHERE a.artist.id = ?1")
    Optional<List<Album>> findByArtistId(UUID artistId);

}
