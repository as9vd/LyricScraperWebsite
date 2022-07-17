package com.asad.geniusanalysis.controller;

import com.asad.geniusanalysis.Scraper;
import com.asad.geniusanalysis.service.DatabaseManager;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.io.IOException;

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
        System.out.println("This is a page for debugging purposes.");

        return "testDb.html";
    }

}