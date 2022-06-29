package com.asad.geniusanalysis.service;

import com.asad.geniusanalysis.entity.Artist;
import com.asad.geniusanalysis.repository.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
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
        return (List<Artist>) artistRepository.findAll();
    }

    @Override
    @Transactional
    public void addArtistsFromCollection() {
        File dir = new File("Collection");

        for (File artistDir: dir.listFiles()) {
            String artistName = artistDir.toString().split("\\\\")[1];

            for (File albumFolder: artistDir.listFiles()) {
                for (File file: albumFolder.listFiles()) {
                    if (file.toString().contains("Tracklist")) {
                        System.out.println(file.toString());
                    }
                }
            }


            if (!(findByName(artistName) == null)) {
                continue;
            } else {
                artistRepository.save(new Artist(artistName));
            }
        }
    }

    @Transactional
    public Artist findByName(String name) {
        for (int i = 0; i < artistRepository.findAll().size(); i++) {
            if (artistRepository.findAll().get(i).getName().equals(name)) {
                return artistRepository.findAll().get(i);
            } else {
                continue;
            }
        }

        return null;
    }

}