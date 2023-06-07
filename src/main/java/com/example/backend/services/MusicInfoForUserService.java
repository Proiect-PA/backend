package com.example.backend.services;

import com.example.backend.dto.MusicEntityTransferBody;
import com.example.backend.exceptions.NonexistentAlbum;
import com.example.backend.exceptions.NonexistentArtist;
import com.example.backend.exceptions.NonexistentTrack;
import com.example.backend.exceptions.NonexistentUser;
import com.example.backend.models.Album;
import com.example.backend.models.Artist;
import com.example.backend.models.Track;
import com.example.backend.models.User;
import com.example.backend.repositories.*;
import com.example.backend.utils.Recommendation;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MusicInfoForUserService {

    private final UsersRepository usersRepository;
    private final AlbumsRepository albumsRepository;
    private final ArtistsRepository artistsRepository;
    private final TracksRepository tracksRepository;

    public MusicInfoForUserService(UsersRepository usersRepository, AlbumsRepository albumsRepository, ArtistsRepository artistsRepository, TracksRepository tracksRepository) {
        this.usersRepository = usersRepository;
        this.albumsRepository = albumsRepository;
        this.artistsRepository = artistsRepository;
        this.tracksRepository = tracksRepository;
    }

    @Transactional
    public void addFavouriteAlbum(MusicEntityTransferBody<Album> albumFavouriteBody) throws NonexistentUser, NonexistentAlbum {
        Optional<User> user = usersRepository.findById(albumFavouriteBody.getUserId());
        if(user.isPresent()) {
            Optional<Album> album = albumsRepository.findByTitle(albumFavouriteBody.getEntity().getTitle());
            if(album.isPresent()) {
                user.get().addFavouriteAlbum(album.get());
                usersRepository.save(user.get());
            } else {
                throw new NonexistentAlbum();
            }
        } else {
            throw new NonexistentUser();
        }
    }

    @Transactional
    public void addRecentAlbum(MusicEntityTransferBody<Album> albumRecentBody) throws NonexistentUser, NonexistentAlbum {
        Optional<User> user = usersRepository.findById(albumRecentBody.getUserId());
        if(user.isPresent()) {
            Optional<Album> album = albumsRepository.findByTitle(albumRecentBody.getEntity().getTitle());
            if(album.isPresent()) {
                user.get().addLastPlayedAlbum(album.get());
                user.get().checkChangeFavouriteAlbum(album.get());
                usersRepository.save(user.get());
            } else {
                throw new NonexistentAlbum();
            }
        } else {
            throw new NonexistentUser();
        }
    }

    @Transactional
    public void removeFavouriteAlbum(MusicEntityTransferBody<Album> albumFavouriteBody) throws NonexistentUser, NonexistentAlbum {
        Optional<User> user = usersRepository.findById(albumFavouriteBody.getUserId());
        if(user.isPresent()) {
            Optional<Album> album = albumsRepository.findByTitle(albumFavouriteBody.getEntity().getTitle());
            if(album.isPresent()) {
                user.get().removeFavouriteAlbum(album.get());
                usersRepository.save(user.get());
            } else {
                throw new NonexistentAlbum();
            }
        }  else {
            throw new NonexistentUser();
        }
    }

    @Transactional
    public void addFavouriteArtist(MusicEntityTransferBody<Artist> artistFavouriteBody) throws NonexistentUser, NonexistentArtist {
        Optional<User> user = usersRepository.findById(artistFavouriteBody.getUserId());
        if(user.isPresent()) {
            Optional<Artist> artist = artistsRepository.findByName(artistFavouriteBody.getEntity().getName());
            if(artist.isPresent()) {
                user.get().addFavouriteArtist(artist.get());
                usersRepository.save(user.get());
            } else {
                throw new NonexistentArtist();
            }
        }  else {
            throw new NonexistentUser();
        }
    }
    @Transactional
    public void addRecentArtist(MusicEntityTransferBody<Artist> artistRecentBody) throws NonexistentUser, NonexistentArtist {
        Optional<User> user = usersRepository.findById(artistRecentBody.getUserId());
        if(user.isPresent()) {
            Optional<Artist> artist = artistsRepository.findByName(artistRecentBody.getEntity().getName());
            if(artist.isPresent()) {
                user.get().addLastPlayedArtist(artist.get());
                user.get().checkChangeFavouriteArtist(artist.get());
                usersRepository.save(user.get());
            } else {
                throw new NonexistentArtist();
            }
        } else {
            throw new NonexistentUser();
        }
    }

    @Transactional
    public void removeFavouriteArtist(MusicEntityTransferBody<Artist> artistFavouriteBody) throws NonexistentUser, NonexistentArtist {
        Optional<User> user = usersRepository.findById(artistFavouriteBody.getUserId());
        if(user.isPresent()) {
            Optional<Artist> artist = artistsRepository.findByName(artistFavouriteBody.getEntity().getName());
            if(artist.isPresent()) {
                user.get().removeFavouriteArtist(artist.get());
                usersRepository.save(user.get());
            } else {
                throw new NonexistentArtist();
            }
        } else {
            throw new NonexistentUser();
        }
    }


    @Transactional
    public void addFavouriteTrack(MusicEntityTransferBody<Track> trackFavouriteBody) throws NonexistentUser, NonexistentTrack {
        Optional<User> user = usersRepository.findById(trackFavouriteBody.getUserId());
        if(user.isPresent()) {
            Optional<Track> track = tracksRepository.findByName(trackFavouriteBody.getEntity().getName());
            if(track.isPresent()) {
                user.get().addFavouriteTrack(track.get());
                usersRepository.save(user.get());
            } else {
                throw new NonexistentTrack();
            }
        }
        else {
            throw new NonexistentUser();
        }
    }

    @Transactional
    public void addRecentTrack(MusicEntityTransferBody<Track> trackRecentBody) throws NonexistentUser, NonexistentTrack {
        Optional<User> user = usersRepository.findById(trackRecentBody.getUserId());
        if(user.isPresent()) {
            Optional<Track> track = tracksRepository.findByName(trackRecentBody.getEntity().getName());
            if(track.isPresent()) {
                user.get().addLastPlayedTrack(track.get());
                user.get().checkChangeFavouriteTrack(track.get());
                usersRepository.save(user.get());
            } else {
                throw new NonexistentTrack();
            }
        }  else {
            throw new NonexistentUser();
        }
    }


    @Transactional
    public void removeFavouriteTrack(MusicEntityTransferBody<Track> trackFavouriteBody) throws NonexistentUser, NonexistentTrack {
        Optional<User> user = usersRepository.findById(trackFavouriteBody.getUserId());
        if(user.isPresent()) {
            Optional<Track> track = tracksRepository.findByName(trackFavouriteBody.getEntity().getName());
            if(track.isPresent()) {
                user.get().removeFavouriteTrack(track.get());
                usersRepository.save(user.get());
            } else {
                throw new NonexistentTrack();
            }
        }  else {
            throw new NonexistentUser();
        }
    }

    public Set<Track> getTracksRecommendations(UUID userId) throws NonexistentUser {
        Optional<User> user = usersRepository.findById(userId);
        if(user.isPresent()) {
            Recommendation graphRecommendations = new Recommendation(user.get(), tracksRepository, albumsRepository);
            return graphRecommendations.getRecommendedTracks();
        } else {
            throw new NonexistentUser();
        }
    }

    public Set<Album> getAlbumsRecommendations(UUID userId) throws NonexistentUser {
        Optional<User> user = usersRepository.findById(userId);
        if(user.isPresent()) {
            Recommendation graphRecommendations = new Recommendation(user.get(), tracksRepository, albumsRepository);
            return graphRecommendations.getRecommendedAlbums();
        } else {
            throw new NonexistentUser();
        }
    }

    public Set<Artist> getArtistsRecommendations(UUID userId) throws NonexistentUser {
        Optional<User> user = usersRepository.findById(userId);
        if(user.isPresent()) {
            Recommendation graphRecommendations = new Recommendation(user.get(), tracksRepository, albumsRepository);
            return graphRecommendations.getRecommendedArtists();
        } else {
            throw new NonexistentUser();
        }
    }
}
