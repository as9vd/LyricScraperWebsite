package com.asad.geniusanalysis.repository;

import com.asad.geniusanalysis.entity.Artist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArtistRepository extends JpaRepository<Artist, Long> {

}