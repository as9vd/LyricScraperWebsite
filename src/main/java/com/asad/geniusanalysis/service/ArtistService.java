package com.asad.geniusanalysis.service;

import com.asad.geniusanalysis.entity.Artist;

import java.util.List;
import java.util.Optional;

public interface ArtistService {
    // C
    void createArtist(Artist artist);

    // R
    Optional<Artist> getArtist(int id);

    // D
    void deleteById(int id);

    List<Artist> getAllArtists();

    void addArtistsFromCollection();

}