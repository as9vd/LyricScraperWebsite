package com.asad.geniusanalysis.service;

import com.asad.geniusanalysis.entity.Album;
import com.asad.geniusanalysis.entity.Artist;
import com.asad.geniusanalysis.entity.Song;
import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.*;
import java.util.ArrayList;

@Service
public class DatabaseManager {
    public ArtistServiceImpl artistService;
    public SongServiceImpl songService;

    @Autowired
    public DatabaseManager(ArtistServiceImpl artistService, SongServiceImpl songService) {
        this.artistService = artistService;
        this.songService = songService;
    }

    @Transactional
    public void addFromCollection() {
        File dir = new File("Collection");

        ArrayList<String> artistsAdded = new ArrayList<>(); // Need this, because findByName doesn't work here, because this is a transactional method.

        for (File artistDir: dir.listFiles()) {
            String artistName = artistDir.toString().split("\\\\")[1];

            if (!(artistService.getByName(artistName) == null)) { // e.g. if you refresh after the database sweeps through the Collection folder.
                continue;
            }

            if (!(artistsAdded.contains(artistName))) { // If this artist hasn't been added to the database yet.
                artistService.createArtist(new Artist(artistName));
                artistsAdded.add(artistName);
            }

            for (File albumFolder : artistDir.listFiles()) { // e.g. Children of "Joey Bada$$"
                for (File file : albumFolder.listFiles()) { // e.g. Children of "Joey Bada$$ - 1999"
                    if (file.toString().contains("Tracklist")) {
                        File tracklistFile = new File(file.getAbsolutePath());

                        try (BufferedReader br = new BufferedReader(new FileReader(tracklistFile))) {
                            String link;
                            String albumName;
                            String songName;
                            boolean debounce = true;

                            final WebClient webClient = new WebClient(BrowserVersion.CHROME);
                            webClient.getOptions().setCssEnabled(false);
                            webClient.getOptions().setJavaScriptEnabled(false);

                            while ((link = br.readLine()) != null) { // Parsing the links bud!
                                final HtmlPage page = webClient.getPage(link);

                                final HtmlElement albumCard = page.getFirstByXPath("/html/body/div[1]/main/div[3]/div[3]/div[1]/div[5]");
                                HtmlElement smallerAlbumCard = albumCard.getFirstByXPath("//*[@id=\"application\"]/main/div[3]/div[3]/div[1]/div[5]/div[3]/a");

                                albumName = smallerAlbumCard.getFirstChild().getTextContent();

                                final HtmlElement titleCard = page.getFirstByXPath("/html/body/div[1]/main/div[1]/div[3]/div/h1/span");
                                songName = titleCard.getFirstChild().getTextContent();

                                Song song = new Song(songName, link);
                                song.setArtist(artistService.getByName(artistName));

                                Album album = new Album(albumName);
                                album.setArtist(artistService.getByName(artistName));

                                song.setAlbum(album);

                                songService.createSong(song);
                            }
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }

}