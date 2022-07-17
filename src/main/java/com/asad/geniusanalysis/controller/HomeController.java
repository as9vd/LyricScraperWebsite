package com.asad.geniusanalysis.controller;

import com.asad.geniusanalysis.service.DatabaseManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
    @Autowired
    public DatabaseManager databaseManager;

    @RequestMapping("/")
    public String index() {
        return "index.html";
    }

    @RequestMapping("/loadDB")
    public String loadDB(Model model) {
        databaseManager.addFromCollection();

        model.addAttribute("artistList", databaseManager.getArtistService().getAllArtists());
        model.addAttribute("albumList", databaseManager.getAlbumService().getAllAlbums());
        model.addAttribute("songList", databaseManager.getSongService().getAllSongs());

        return "loadDB.html";
    }

    @RequestMapping("/testDb")
    public String testDb(Model model) {
//        System.out.println(databaseManager.getArtistService().getAllArtists());
//        model.addAttribute("artistname", databaseManager.getArtistService().getAllArtists().get(2).getAlbums());

//        int songCount = databaseManager.getSongService().getAllSongs().size();
//
//        for (int i = 0; i < songCount; i++) {
//            System.out.println(databaseManager.getSongService().getAllSongs().get(i).artist.id);
//        }


        return "testDb.html";
    }

}