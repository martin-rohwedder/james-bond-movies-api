/**
  Directors table
 */
CREATE TABLE directors (
   id BINARY(16) NOT NULL,
   name VARCHAR(255),
   biography TEXT,
   nationality VARCHAR(255),
   date_of_birth DATE,
   date_of_death DATE,
   created_at DATETIME(6) NOT NULL,
   updated_at DATETIME(6) NOT NULL,

   PRIMARY KEY (id)
);

/**
  Music Table
 */
CREATE TABLE music (
   id BINARY(16) NOT NULL,
   title VARCHAR(100),
   performer VARCHAR(100),
   song_url VARCHAR(100),
   created_at DATETIME(6) NOT NULL,
   updated_at DATETIME(6) NOT NULL,

   PRIMARY KEY (id)
);

/**
  Movies table
 */
CREATE TABLE movies (
    id BINARY(16) NOT NULL,
    movie_number INT,
    title VARCHAR(100),
    short_description VARCHAR(255),
    long_description TEXT,
    trailer_url VARCHAR(100),
    world_premiere VARCHAR(255),
    music_id BINARY(16),
    james_bond_actor VARCHAR(255),
    director_id BINARY(16),
    locations VARCHAR(1000),
    created_at DATETIME(6) NOT NULL,
    updated_at DATETIME(6) NOT NULL,

    PRIMARY KEY (id),

    CONSTRAINT uk_movies_music_id
        UNIQUE (music_id),

    CONSTRAINT fk_movies_music
        FOREIGN KEY (music_id)
            REFERENCES music (id),

    CONSTRAINT fk_movies_director
        FOREIGN KEY (director_id)
            REFERENCES directors (id)
);

/**
  Release Dates table
 */
CREATE TABLE release_dates (
   id BINARY(16) NOT NULL,
   date_of_release DATE,
   country VARCHAR(255),
   country_code VARCHAR(255),
   movie_id BINARY(16),
   created_at DATETIME(6) NOT NULL,
   updated_at DATETIME(6) NOT NULL,

   PRIMARY KEY (id),

   CONSTRAINT fk_release_dates_movie
       FOREIGN KEY (movie_id)
           REFERENCES movies (id)
);

/**
  Producers table
 */
CREATE TABLE producers (
   id BINARY(16) NOT NULL,
   name VARCHAR(255),
   biography TEXT,
   nationality VARCHAR(255),
   date_of_birth DATE,
   date_of_death DATE,
   created_at DATETIME(6) NOT NULL,
   updated_at DATETIME(6) NOT NULL,

   PRIMARY KEY (id)
);

/**
  Movies and Producers join table
 */
CREATE TABLE movies_producers (
    movie_id BINARY(16) NOT NULL,
    producer_id BINARY(16) NOT NULL,

    PRIMARY KEY (movie_id, producer_id),

    CONSTRAINT fk_movies_producers_movie
      FOREIGN KEY (movie_id)
          REFERENCES movies (id),

    CONSTRAINT fk_movies_producers_producer
      FOREIGN KEY (producer_id)
          REFERENCES producers (id)
);

/**
  Actors table
 */
CREATE TABLE actors (
    id BINARY(16) NOT NULL,
    name VARCHAR(50),
    character_role VARCHAR(50),
    biography TEXT,
    nationality VARCHAR(50),
    date_of_birth DATE,
    date_of_death DATE,
    created_at DATETIME(6) NOT NULL,
    updated_at DATETIME(6) NOT NULL,

    PRIMARY KEY (id)
);

/**
  Movies and Actors join table
 */
CREATE TABLE movies_actors (
    movie_id BINARY(16) NOT NULL,
    actor_id BINARY(16) NOT NULL,

    PRIMARY KEY (movie_id, actor_id),

    CONSTRAINT fk_movies_actors_movie
       FOREIGN KEY (movie_id)
           REFERENCES movies (id),

    CONSTRAINT fk_movies_actors_actor
       FOREIGN KEY (actor_id)
           REFERENCES actors (id)
);