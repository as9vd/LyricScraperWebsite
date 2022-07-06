package com.asad.geniusanalysis.service.Album;

import com.asad.geniusanalysis.entity.Album;
import com.asad.geniusanalysis.entity.Artist;

import java.util.List;
import java.util.Optional;

public interface AlbumService {
    void createAlbum(Album artist);

    // R
    Optional<Album> getAlbum(int id);

    // D
    void deleteById(int id);

    List<Album> getAllAlbums();

    public Album getByName(String name);

}