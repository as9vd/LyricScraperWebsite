package com.asad.geniusanalysis.repository;

import com.asad.geniusanalysis.entity.Album;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin("http://localhost:4200")
public interface AlbumRepository extends JpaRepository<Album, Integer> {

}