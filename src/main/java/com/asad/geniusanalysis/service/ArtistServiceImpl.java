package com.asad.geniusanalysis.service;

import com.asad.geniusanalysis.entity.Artist;
import com.asad.geniusanalysis.entity.Song;
import com.asad.geniusanalysis.repository.ArtistRepository;
import com.asad.geniusanalysis.repository.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ArtistServiceImpl implements ArtistService {
    private ArtistRepository artistRepository;
    private SongRepository songRepository;

    @Autowired
    public ArtistServiceImpl(ArtistRepository artistRepository, SongRepository songRepository) {
        this.artistRepository = artistRepository;
        this.songRepository = songRepository;
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
    public Artist getByName(String name) {
        for (int i = 0; i < artistRepository.findAll().size(); i++) {
            if (artistRepository.findAll().get(i).getName().equals(name)) {
                return artistRepository.findAll().get(i);
            }
        }

        return null;
    }

    @Override
    @Transactional
    public void addArtistsFromCollection() {
        File dir = new File("Collection");

        ArrayList<String> artistsAdded = new ArrayList<>(); // Need this, because findByName doesn't work here, because this is a transactional method.

        for (File artistDir: dir.listFiles()) {
            String artistName = artistDir.toString().split("\\\\")[1];

            if (!(getByName(artistName) == null)) { // e.g. if you refresh after the database sweeps through the Collection folder.
                continue;
            }

            if (!(artistsAdded.contains(artistName))) {
                artistRepository.save(new Artist(artistName));
                artistsAdded.add(artistName);
            }

            for (File albumFolder: artistDir.listFiles()) { // e.g. Children of "Joey Bada$$"
                for (File file: albumFolder.listFiles()) { // e.g. Children of "Joey Bada$$ - 1999"
                    if (file.toString().contains("Tracklist")) {
                        File tracklistFile = new File(file.getAbsolutePath());

                        try (BufferedReader br = new BufferedReader(new FileReader(tracklistFile))) {
                            String line;
                            while ((line = br.readLine()) != null) { // Parsing the links bud!
                                Song song = new Song(line);
                                song.setArtist(getByName(artistName));

                                songRepository.save(song);
                            }
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
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