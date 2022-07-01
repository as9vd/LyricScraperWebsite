CREATE TABLE  artist_list_fk (
    id INT AUTO_INCREMENT,
    stage_name CHAR(255),
    
    PRIMARY KEY (id)
);

INSERT INTO artist_list_fk (stage_name) VALUES ("Drake"), ("Kendrick Lamar"), ("Rick Ross"), ("J. Cole"), ("The Weeknd"), ("Chief Keef");

CREATE TABLE song_links (
	id INT AUTO_INCREMENT,
    link CHAR(255) NOT NULL,
    artistID int NOT NULL,
    
    PRIMARY KEY (id),
    FOREIGN KEY (artistID) REFERENCES artist_list_fk(id)
);