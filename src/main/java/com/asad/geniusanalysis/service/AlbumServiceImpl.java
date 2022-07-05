package com.asad.geniusanalysis.service;

import com.asad.geniusanalysis.entity.Album;
import com.asad.geniusanalysis.entity.Artist;
import com.asad.geniusanalysis.repository.AlbumRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class AlbumServiceImpl implements AlbumService {
    private AlbumRepository albumRepository;

    @Autowired
    public AlbumServiceImpl(AlbumRepository albumRepository) {
        this.albumRepository = albumRepository;
    }

    @Override
    public void createAlbum(Album album) {
        albumRepository.save(album);
    }

    @Override
    public Optional<Album> getAlbum(int id) {
        return albumRepository.findById(id);
    }

    @Override
    public void deleteById(int id) {
        albumRepository.deleteById(id);
    }

    @Override
    public List<Album> getAllAlbums() {
        return albumRepository.findAll();
    }
}