package com.asad.geniusanalysis.service.Recent;

import com.asad.geniusanalysis.entity.RecentLink;
import com.asad.geniusanalysis.entity.Song;
import com.asad.geniusanalysis.repository.RecentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class RecentServiceImpl implements RecentService {
    private RecentRepository recentRepository;

    @Autowired
    public RecentServiceImpl(RecentRepository recentRepository) {
        this.recentRepository = recentRepository;
    }

    @Transactional
    @Override public void createRecent(RecentLink recentLink) {
        this.recentRepository.save(recentLink);
    }

    @Transactional
    @Override public Optional<RecentLink> getRecent(int id) {
        return recentRepository.findById(id);
    }

    @Transactional
    @Override public void deleteById(int id) {
        recentRepository.deleteById(id);
    }

    @Override public List<RecentLink> getAllRecents() {
        return recentRepository.findAll();
    }
}
