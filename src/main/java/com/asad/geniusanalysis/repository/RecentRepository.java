package com.asad.geniusanalysis.repository;

import com.asad.geniusanalysis.entity.RecentLink;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin("*")
public interface RecentRepository extends JpaRepository<RecentLink, Integer> {

}
