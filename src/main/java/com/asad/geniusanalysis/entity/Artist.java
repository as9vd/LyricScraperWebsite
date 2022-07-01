package com.asad.geniusanalysis.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "artist_genius_list")
public class Artist {
    @Id
    @Column(name = "id")
    public int id;

    @Column(name = "stage_name")
    public String name;

}
