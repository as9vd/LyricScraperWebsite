package com.asad.geniusanalysis.repository;

import com.asad.geniusanalysis.entity.RecentLink;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin("http://localhost:4200")
public interface RecentRepository extends JpaRepository<RecentLink, Integer> {

}
