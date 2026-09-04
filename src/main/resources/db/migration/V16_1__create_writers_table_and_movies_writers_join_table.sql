-- Writers table
CREATE TABLE writers (
    id BINARY(16) NOT NULL,
    name VARCHAR(50) NOT NULL,
    date_of_birth DATE,
    date_of_death DATE,
    created_at DATETIME(6) NOT NULL,
    updated_at DATETIME(6) NOT NULL,

    PRIMARY KEY (id),

    CONSTRAINT uk_writers_name
        UNIQUE (name)
);

-- Join table for movies and writers
CREATE TABLE movies_writers (
    movie_id BINARY(16) NOT NULL,
    writer_id BINARY(16) NOT NULL,

    PRIMARY KEY (movie_id, writer_id),

    CONSTRAINT fk_movies_writers_movie
        FOREIGN KEY (movie_id)
            REFERENCES movies (id),

    CONSTRAINT fk_movies_writers_writer
        FOREIGN KEY (writer_id)
            REFERENCES writers (id)
);