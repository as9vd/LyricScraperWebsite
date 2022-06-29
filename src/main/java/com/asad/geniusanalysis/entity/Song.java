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
    public int artistID;

    public Song() {}

    public Song(String link) {
        this.link = link;
    }

    public Song(int id, String link, int artistID) {
        this.id = id;
        this.link = link;
        this.artistID = artistID;
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

    public int getArtistID() {
        return artistID;
    }

    public void setArtistID(int artistID) {
        this.artistID = artistID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Song song = (Song) o;
        return id == song.id && artistID == song.artistID && Objects.equals(link, song.link);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, link, artistID);
    }

    @Override
    public String toString() {
        return "Song{" +
                "id=" + id +
                ", link='" + link + '\'' +
                ", artistID=" + artistID +
                '}';
    }
}