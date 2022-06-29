package com.asad.geniusanalysis.controller;

import com.asad.geniusanalysis.repository.ArtistRepository;
import com.asad.geniusanalysis.service.ArtistServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
    @Autowired
    public ArtistServiceImpl artistService;

    @RequestMapping("/")
    public String index() {
        return "index.html";
    }

    @RequestMapping("/artistList")
    public String artistList(Model model) {
        model.addAttribute("artistList", artistService.getAllArtists());
        return "artistList.html";
    }

}