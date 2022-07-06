package com.asad.geniusanalysis.entity;

import lombok.Data;
import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "artists")
@Data
public class Artist {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;

    @Column(name = "name")
    public String name;

    @OneToMany(mappedBy = "artist", cascade = CascadeType.ALL)
    List<Album> albums;

    public Artist(String name) {
        this.name = name;
    }

    public Artist() {

    }

    @Override
    public String toString() {
        return "Artist{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}