package com.asad.geniusanalysis.service.Song;

import com.asad.geniusanalysis.entity.Song;

import java.util.List;
import java.util.Optional;

public interface SongService {
    // C
    void createSong(Song song);

    // R
    Optional<Song> getSong(int id);

    // D
    void deleteById(int id);

    List<Song> getAllSongs();

}