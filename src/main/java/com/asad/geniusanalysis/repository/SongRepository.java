package com.asad.geniusanalysis.repository;

import com.asad.geniusanalysis.entity.Song;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SongRepository extends JpaRepository<Song, Integer> {

}