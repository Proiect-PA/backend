package com.example.backend.repositories;

import com.example.backend.models.Album;
import com.example.backend.models.Track;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface TracksRepository extends JpaRepository<Track, UUID> {
    @Query("select a from Track a where lower(a.name) = lower(?1)")
    Optional<Track> findByName(String name);

    @Query("select t from Track t where t.album.id = ?1")
    Optional<List<Track>> findByAlbumId(UUID albumId);

    @Query("select t from Track t where t.album.artist.id = ?1")
    Optional<List<Track>> findByArtistId(UUID artistId);


    @Query("select count(t) from Track t")
    int countAll();

    @Query("select t from Track t where t.album.id = ?1")
    Optional<List<Track>> getTrackByAlbumId(UUID albumId);
}
