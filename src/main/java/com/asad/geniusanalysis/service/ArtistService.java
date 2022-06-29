package com.asad.geniusanalysis.service;

import com.asad.geniusanalysis.entity.Artist;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ArtistService {
    // C
    void createArtist(Artist artist);

    // R
    Artist getArtist(int id);

    // D
    void deleteById(int id);

    List<Artist> getAllArtists();

}