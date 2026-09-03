-- Genres table
CREATE TABLE genres (
    id BINARY(16) NOT NULL,
    title VARCHAR(50) NOT NULL,
    created_at DATETIME(6) NOT NULL,
    updated_at DATETIME(6) NOT NULL,

    PRIMARY KEY (id),

    CONSTRAINT uk_genres_title
        UNIQUE (title)
);

-- Join table for movies and genres
CREATE TABLE movies_genres (
    movie_id BINARY(16) NOT NULL,
    genre_id BINARY(16) NOT NULL,

    PRIMARY KEY (movie_id, genre_id),

    CONSTRAINT fk_movies_genres_movie
        FOREIGN KEY (movie_id)
            REFERENCES movies (id),

    CONSTRAINT fk_movies_genres_genre
        FOREIGN KEY (genre_id)
            REFERENCES genres (id)
);