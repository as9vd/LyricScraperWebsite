package com.asad.geniusanalysis.controller;

import com.asad.geniusanalysis.Scraper;
import com.asad.geniusanalysis.entity.RecentLink;
import com.asad.geniusanalysis.service.Recent.RecentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@CrossOrigin("*")
public class ListRestController {
    @Autowired
    public RecentServiceImpl recentService;

    // localhost:8080/persistLink[...]
    @RequestMapping(path = "/persistLink/{link}", method = RequestMethod.GET)
    public String test(@PathVariable String link) throws IOException {
        String baseUrl = "https://genius.com/", geniusUrl = baseUrl + link;
        String words;

        try {
            words = Scraper.createSongJSON(geniusUrl, "temp");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        RecentLink recent = new RecentLink(geniusUrl, words);
        this.recentService.createRecent(recent);

        return recent.words;
    }

    @RequestMapping(path = "/clearRecents", method = RequestMethod.GET)
    public void clearRecents() {
        System.out.println(recentService.getAllRecents());
        while (recentService.getAllRecents().size() > 5) {
            recentService.deleteMostRecent();
        }
    }
}
