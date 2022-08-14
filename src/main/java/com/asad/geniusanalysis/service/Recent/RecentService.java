package com.asad.geniusanalysis.service.Recent;

import com.asad.geniusanalysis.entity.RecentLink;
import com.asad.geniusanalysis.entity.Song;

import java.util.List;
import java.util.Optional;

public interface RecentService {
    // C
    void createRecent(RecentLink recentLink);

    // R
    Optional<RecentLink> getRecent(int id);

    // D
    void deleteById(int id);

    List<RecentLink> getAllRecents();
}
