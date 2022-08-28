package com.asad.geniusanalysis.controller;

import com.asad.geniusanalysis.service.DatabaseManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@CrossOrigin("*")
// A controller classed used to test functionality.
// Also initially to load songs into the database.
public class HomeController {
    @Autowired
    public DatabaseManager databaseManager;

    @RequestMapping("/")
    public String index() {
        return "index.html";
    }

    @RequestMapping("/testDb")
    public String testDb(Model model) {
        System.out.println("This is a page for debugging purposes.");

        return "testDb.html";
    }

}
