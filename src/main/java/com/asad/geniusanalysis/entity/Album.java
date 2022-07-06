package com.asad.geniusanalysis.entity;

import lombok.Data;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "albums")
@Data
public class Album {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column(name = "title")
    String title;

    @ManyToOne(targetEntity = com.asad.geniusanalysis.entity.Artist.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "artist_id")
    Artist artist;

    @OneToMany(mappedBy = "album", cascade = CascadeType.ALL)
    List<Song> songs = new ArrayList<>();

    public Album(String albumName) {
        this.title = albumName;
    }

    public Album() {

    }

    @Override
    public String toString() {
        return "Album{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}';
    }
}