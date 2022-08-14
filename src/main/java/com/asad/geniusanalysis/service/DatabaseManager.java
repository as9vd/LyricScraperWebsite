package com.asad.geniusanalysis.service;

import com.asad.geniusanalysis.entity.Album;
import com.asad.geniusanalysis.entity.Artist;
import com.asad.geniusanalysis.entity.Song;
import com.asad.geniusanalysis.service.Album.AlbumServiceImpl;
import com.asad.geniusanalysis.service.Artist.ArtistServiceImpl;
import com.asad.geniusanalysis.service.Recent.RecentService;
import com.asad.geniusanalysis.service.Recent.RecentServiceImpl;
import com.asad.geniusanalysis.service.Song.SongServiceImpl;
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
    public AlbumServiceImpl albumService;
    public RecentServiceImpl recentService;

    @Autowired
    public DatabaseManager(ArtistServiceImpl artistService, SongServiceImpl songService, AlbumServiceImpl albumService, RecentServiceImpl recentService) {
        this.artistService = artistService;
        this.songService = songService;
        this.albumService = albumService;
        this.recentService = recentService;
    }

    @Transactional
    public void addFromCollection() {
        File dir = new File("Collection");

        ArrayList<String> artistsAdded = new ArrayList<>(); // Need this, because findByName doesn't work here, because this is a transactional method.
        ArrayList<String> albumsAdded = new ArrayList<>();

        for (File artistDir: dir.listFiles()) {
            String artistName = artistDir.toString().split("/")[1];

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

                            final WebClient webClient = new WebClient(BrowserVersion.CHROME);
                            webClient.getOptions().setCssEnabled(false);
                            webClient.getOptions().setJavaScriptEnabled(false);

                            while ((link = br.readLine()) != null) { // Parsing the links bud!
                                final HtmlPage page = webClient.getPage(link);

                                final HtmlElement albumCard = page.getFirstByXPath("/html/body/div[1]/main/div[3]/div[3]/div[1]/div[5]");
                                HtmlElement smallerAlbumCard = albumCard.getFirstByXPath("//*[@id=\"application\"]/main/div[3]/div[3]/div[1]/div[5]/div[3]/a");

                                albumName = smallerAlbumCard.getFirstChild().getTextContent();

                                final HtmlElement titleCard = page.getFirstByXPath("/html/body/div[1]/main/div[1]/div[3]/div/h1/span");
                                // Replacing weird quotes and ZeroWidthSpaces.
                                songName = titleCard.getFirstChild().getTextContent().replace("’", "'").replaceAll("[\\p{Cf}]", "");

                                Song song = new Song(songName, link);
                                song.setArtist(artistService.getByName(artistName));

                                if (!(albumsAdded.contains(albumName))) {
                                    Album album = new Album(albumName);
                                    album.setArtist(artistService.getByName(artistName));

                                    albumService.createAlbum(album);
                                    albumsAdded.add(albumName);
                                }

                                song.setAlbum(albumService.getByName(albumName));

                                System.out.println(albumName + ": " + songName);

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

    public ArtistServiceImpl getArtistService() {
        return artistService;
    }

    public void setArtistService(ArtistServiceImpl artistService) {
        this.artistService = artistService;
    }

    public SongServiceImpl getSongService() {
        return songService;
    }

    public void setSongService(SongServiceImpl songService) {
        this.songService = songService;
    }

    public AlbumServiceImpl getAlbumService() {
        return albumService;
    }

    public void setAlbumService(AlbumServiceImpl albumService) {
        this.albumService = albumService;
    }

    public RecentServiceImpl getRecentService() {return recentService;}

    public void setRecentService(RecentServiceImpl recentService) {this.recentService = recentService;}

}
