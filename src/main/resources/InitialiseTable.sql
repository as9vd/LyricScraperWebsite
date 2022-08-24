CREATE TABLE artists (
                         id INT AUTO_INCREMENT NOT NULL,
                         name CHAR(255) NOT NULL,

                         PRIMARY KEY (id)
);

CREATE TABLE albums (
                        id INT AUTO_INCREMENT NOT NULL,
                        title CHAR(255) NOT NULL,
                        artist_id INT NOT NULL,

                        PRIMARY KEY (id),
                        FOREIGN KEY (artist_id) REFERENCES artists(id)
);

CREATE TABLE songs (
                       id INT AUTO_INCREMENT NOT NULL,
                       link CHAR(255) NOT NULL,
                       title CHAR(225) NOT NULL,
                       artist_id INT NOT NULL,
                       album_id INT NOT NULL,

                       PRIMARY KEY (id),
                       FOREIGN KEY (artist_id) REFERENCES artists(id),
                       FOREIGN KEY (album_id) REFERENCES albums(id)
);

CREATE TABLE `recents` (
                           `id` int NOT NULL AUTO_INCREMENT,
                           `link` char(255) NOT NULL,
                           `words` JSON NOT NULL,
                           PRIMARY KEY (`id`)
);
