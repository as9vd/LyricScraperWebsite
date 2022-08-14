package com.asad.geniusanalysis.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "recent")
@Data
public class RecentLink {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;

    @Column(name = "link")
    public String link;
}
