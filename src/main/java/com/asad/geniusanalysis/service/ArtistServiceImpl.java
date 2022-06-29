package com.asad.geniusanalysis.service;

import com.asad.geniusanalysis.entity.Artist;
import com.asad.geniusanalysis.repository.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ArtistServiceImpl implements ArtistService {
    private ArtistRepository artistRepository;

    @Autowired
    public ArtistServiceImpl(ArtistRepository artistRepository) {
        this.artistRepository = artistRepository;
    }

    @Override
    @Transactional
    public void createArtist(Artist artist) {
        artistRepository.save(artist);
    }

    @Override
    @Transactional
    public Artist getArtist(int id) {
        return artistRepository.getReferenceById((long) id);
    }

    @Override
    @Transactional
    public void deleteById(int id) {
        artistRepository.deleteById((long) id);
    }

    @Override
    public List<Artist> getAllArtists() {
        return artistRepository.findAll();
    }
}