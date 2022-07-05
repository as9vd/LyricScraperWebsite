package com.asad.geniusanalysis.service.Song;

import com.asad.geniusanalysis.entity.Song;
import com.asad.geniusanalysis.repository.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class SongServiceImpl implements SongService {
    private SongRepository songRepository;

    @Autowired
    public SongServiceImpl(SongRepository songRepository) {
        this.songRepository = songRepository;
    }

    @Transactional
    @Override
    public void createSong(Song song) {
        songRepository.save(song);
    }

    @Transactional
    @Override
    public Optional<Song> getSong(int id) {
        return songRepository.findById(id);
    }

    @Transactional
    @Override
    public void deleteById(int id) {
        songRepository.deleteById(id);
    }

    @Override
    public List<Song> getAllSongs() {
        return songRepository.findAll();
    }

}