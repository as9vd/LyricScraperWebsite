package com.asad.geniusanalysis.service.Artist;

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

    Artist getByName(String name);

}