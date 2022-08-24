package com.asad.geniusanalysis.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "recents")
@Data
public class RecentLink {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;

    @Column(name = "link")
    public String link;

    public RecentLink() {
    }

    public RecentLink(String link) {
        this.link = link;
    }
}
