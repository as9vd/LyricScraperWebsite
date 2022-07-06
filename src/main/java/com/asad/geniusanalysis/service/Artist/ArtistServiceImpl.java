package com.asad.geniusanalysis.service.Artist;

import com.asad.geniusanalysis.entity.Artist;
import com.asad.geniusanalysis.repository.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

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
    public Optional<Artist> getArtist(int id) {
        return artistRepository.findById(id);
    }

    @Override
    @Transactional
    public void deleteById(int id) {
        artistRepository.deleteById(id);
    }

    @Override
    @Transactional
    public List<Artist> getAllArtists() {
        return artistRepository.findAll();
    }

    @Override
    public Artist getByName(String name) {
        for (int i = 0; i < artistRepository.findAll().size(); i++) {
            if (artistRepository.findAll().get(i).getName().equals(name)) {
                return artistRepository.findAll().get(i);
            }
        }

        return null;
    }

}