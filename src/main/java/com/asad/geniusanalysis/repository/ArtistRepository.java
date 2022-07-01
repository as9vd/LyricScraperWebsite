package com.asad.geniusanalysis.repository;

import com.asad.geniusanalysis.entity.Artist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface ArtistRepository extends JpaRepository<Artist, Integer> {

}