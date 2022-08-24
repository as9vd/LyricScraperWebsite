package com.asad.geniusanalysis.entity;

import com.fasterxml.jackson.annotation.JsonRawValue;
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

    @Column(name = "words", columnDefinition = "json")
    @JsonRawValue
    public String words;

    public RecentLink() {
    }

    public RecentLink(String link) {
        this.link = link;
    }

    public RecentLink(String link, String json) {
        this.link = link;
        this.words = json;
    }

}
