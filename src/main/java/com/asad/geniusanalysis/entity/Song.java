package com.asad.geniusanalysis.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import javax.persistence.*;

@Entity
@Table(name = "songs")
@Data
public class Song {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;

    @Column(name = "link")
    public String link;

    @Column(name = "title")
    public String title;

    @ManyToOne(targetEntity = com.asad.geniusanalysis.entity.Artist.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "artist_id")
    public Artist artist;

    @ManyToOne(targetEntity = com.asad.geniusanalysis.entity.Album.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "album_id")
    private Album album;

    public Song(String title) {
        this.title = title;
    }

    public Song(String title, String link) {
        this.title = title;
        this.link = link;
    }

    public Song() {

    }

    @Override
    public String toString() {
        return "Song{" +
                "id=" + id +
                ", link='" + link + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}