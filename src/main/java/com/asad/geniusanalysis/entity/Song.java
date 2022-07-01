package com.asad.geniusanalysis.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "song_links")
public class Song {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;

    @Column(name = "link")
    public String link;

    @OneToOne(targetEntity = com.asad.geniusanalysis.entity.Artist.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "artistID")
    public Artist artist;

    public Song() {}

    public Song(String link) {
        this.link = link;
    }

    public Song(int id, String link, Artist artist) {
        this.id = id;
        this.link = link;
        this.artist = artist;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Song song = (Song) o;
        return id == song.id && Objects.equals(link, song.link) && Objects.equals(artist, song.artist);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, link, artist);
    }

    @Override
    public String toString() {
        return "Song{" +
                "id=" + id +
                ", link='" + link + '\'' +
                ", artist=" + artist +
                '}';
    }
}