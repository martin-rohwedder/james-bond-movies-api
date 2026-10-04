[![Java CI with Maven](https://github.com/martin-rohwedder/james-bond-movies-api/actions/workflows/maven.yml/badge.svg)](https://github.com/martin-rohwedder/james-bond-movies-api/actions/workflows/maven.yml)
![Coverage](.github/badges/jacoco.svg)
![Java](https://img.shields.io/badge/Java-25-d15e5c?logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-4.1.1-6DB33F?logo=springboot&logoColor=6DB33F)
![MySQL](https://img.shields.io/badge/MySQL-8.0-4479A1?logo=mysql&logoColor=a5cae6)

# James Bond Movies API

API built with Spring Boot for James Bond movies. The API has support for both *REST* and *GraphQL*

# How to get started

### Prerequisites

- Java 25
- Docker Desktop
- IntelliJ IDEA (Recommended)

### Run locally

1. Clone the repository.

- `git clone https://github.com/martin-rohwedder/james-bond-movies-api.git`
- `cd james-bond-movies-api`

2. Create a `.env` file in the project root.

- `cp .env.example .env`

3. Edit `.env` and replace the placeholder values

```dotenv
MYSQL_DATABASE=jb_api_db
MYSQL_USER=myuser
MYSQL_PASSWORD=secret
MYSQL_ROOT_PASSWORD=verysecret

API_KEY=your-very-long-random-api-key
```

4. Open the project in IntelliJ IDEA.
5. Start the Spring Boot application from IntelliJ.

Spring Boot’s Docker Compose integration will automatically start the MySQL container, apply the Flyway migrations, and connect the application to the database.

# How to deploy

The repository includes a production-ready `compose.yaml` file that pulls the API image from GitHub Container Registry (GHCR).

## Prerequisites

- Docker Desktop (Or Docker Engine on a server)

### 1. Create the `compose.yaml` file

Ensure the `compose.yaml` file exists with the following:

```yml
services:
  mysql:
    image: 'mysql:8.0.46-debian'
    restart: unless-stopped
    environment:
      MYSQL_DATABASE: ${MYSQL_DATABASE}
      MYSQL_PASSWORD: ${MYSQL_PASSWORD}
      MYSQL_ROOT_PASSWORD: ${MYSQL_ROOT_PASSWORD}
      MYSQL_USER: ${MYSQL_USER}
    command:
      - --character-set-server=utf8mb4
      - --collation-server=utf8mb4_0900_ai_ci
    ports:
      - '3306:3306'
    volumes:
      - jb_api_mysql_data:/var/lib/mysql
    healthcheck:
      test: [ "CMD", "mysqladmin", "ping", "-h", "localhost" ]
      interval: 10s
      timeout: 5s
      retries: 10
      start_period: 20s

  jb_api:
    profiles: [ "deploy" ]
    image: ghcr.io/martin-rohwedder/james-bond-movies-api:latest
    restart: on-failure:3
    depends_on:
      mysql:
        condition: service_healthy
    ports:
      - "8080:8080"
    environment:
      SPRING_DATASOURCE_URL: jdbc:mysql://mysql:3306/${MYSQL_DATABASE}
      SPRING_DATASOURCE_USERNAME: ${MYSQL_USER}
      SPRING_DATASOURCE_PASSWORD: ${MYSQL_PASSWORD}
      APP_SECURITY_API_KEY: ${API_KEY}
    healthcheck:
      test: [ "CMD", "wget", "--spider", "-q", "http://localhost:8080/actuator/health" ]
      interval: 30s
      timeout: 5s
      retries: 3
      start_period: 30s

volumes:
  jb_api_mysql_data:
```

### 2. Create a `.env` file

Ensure a `.env` file exists, with the following values:

```dotenv
MYSQL_DATABASE=jb_api_db
MYSQL_USER=myuser
MYSQL_PASSWORD=secret
MYSQL_ROOT_PASSWORD=verysecret

API_KEY=your-very-long-random-api-key
```

### 3. Start the application

Use `docker compose --profile deploy up -d`

This starts:
- **MySQL** on port `3306`
- **James Bond Movies API** on port `8080`

The API image is automatically pulled from **GitHub Container Registry (GHCR)**.

Verify the deployment at: `http://localhost:8080/swagger-ui/index.html`

### Stop the application

Use `docker compose --profile deploy down`

To remove the MySQL data volume as well: `docker compose --profile deploy down -v`

# API Documentation

When the API is running locally, interactive API documentation is available through **Swagger UI**.

- **Swagger UI:** http://localhost:8080/swagger-ui/index.html
- **OpenAPI JSON:** http://localhost:8080/v3/api-docs

Swagger UI allows you to explore the available endpoints and execute requests directly from the browser.

## Authentication

All API endpoints (except the documentation endpoints) require an **API key**.

Include the API key in the **X-API-Key request header**.

```bash
curl -H "X-API-Key: your-very-long-random-api-key" \
  http://localhost:8080/api/movies
```

In Swagger UI, click **Authorize** and enter your API key once. It will automatically be included in subsequent requests.

## REST Endpoints Overview

An overview of all endpoints

### Movies

| **Method** 	| **Endpoint**       	                                                                                      | **Description**   	                                                  |
|------------	|----------------------------------------------------------------------------------------------------------|----------------------------------------------------------------------|
| GET        	| `/api/movies`      	                                                                                      | List all movies (defaults include actors, producers and trivias)   	 |
| GET        	| `/api/movies?excludeActors={true/false}&excludeProducers={true/false}&excludeTrivias={true/false}`      	 | List all movies (optional exclusion of actors, producers and/or trivias) |
| GET        	| `/api/movies/{id}` 	                                                                                      | Get a movie by id (defaults include actors, producers and trivias)   |
| GET        	| `/api/movies/{id}?excludeActors={true/false}&excludeProducers={true/false}&excludeTrivias={true/false}` 	 | Get a movie by id (optional exclusions of actors, producers and/or trivias |
| GET        	| `/api/movies/{id}/actors` 	 | Get a movie by id (minimal data), with only actors |
| GET        	| `/api/movies/{id}/producers` 	 | Get a movie by id (minimal data), with only producers |
| GET        	| `/api/movies/{id}/director` 	 | Get a movie by id (minimal data), with only director |
| GET        	| `/api/movies/{id}/writers` 	 | Get a movie by id (minimal data), with only writers |
| GET        	| `/api/movies/{id}/trivias` 	 | Get a movie by id (minimal data), with only trivias |

### Actors

| **Method** 	| **Endpoint**       	                                        | **Description**   	                     |
|------------	|------------------------------------------------------------|----------------------------------------|
| GET        	| `/api/actors`      	                                        | List all actors   	                     |
| GET        	| `/api/actors/{id}` 	                                        | Get an actor by id 	                    |
| GET        	| `/api/actors?name={actor_name}`	                            | Get an actor by name	                   |
| GET        	| `/api/actors?name={actor_name}&includeMovies={true/false}`	 | Get an actor by name	and include/exclude movies |

### Directors

| **Method** 	| **Endpoint**       	                    | **Description**   	      |
|------------	|----------------------------------------|-------------------------|
| GET        	| `/api/directors`      	                 | List all directors   	   |
| GET        	| `/api/directors/{id}` 	                 | Get a director by id 	   |
| GET        	| `/api/directors?name={director_name}` 	 | Get a director by name 	 |
| GET        	| `/api/directors?name={director_name}&includeMovies={true/false}` 	 | Get a director by name and include/exclude movies 	 |

### Producers

| **Method** 	| **Endpoint**       	                    | **Description**   	      |
|------------	|----------------------------------------|-------------------------|
| GET        	| `/api/producers`      	                 | List all producers   	   |
| GET        	| `/api/prodcuers/{id}` 	                 | Get a producer by id 	   |
| GET        	| `/api/producers?name={producer_name}` 	 | Get a producer by name 	 |
| GET        	| `/api/producers?name={producer_name}&includeMovies={true/false}` 	 | Get a producer by name and include/exclude movies 	 |

### Writers

| **Method** 	| **Endpoint**       	                    | **Description**   	      |
|------------	|----------------------------------------|-------------------------|
| GET        	| `/api/writers`      	                 | List all writers   	   |
| GET        	| `/api/writers/{id}` 	                 | Get a writer by id 	   |
| GET        	| `/api/writers?name={writer_name}` 	 | Get a writer by name 	 |
| GET        	| `/api/writers?name={writer_name}&includeMovies={true/false}` 	 | Get a writer by name and include/exclude movies 	 |

### Music

| **Method** 	| **Endpoint**       	                      | **Description**   	      |
|------------	|------------------------------------------|-------------------------|
| GET        	| `/api/music`      	                       | List all music   	       |
| GET        	| `/api/music/{id}`      	                  | Get music by id   	      |
| GET        	| `/api/music?performer={performer_name}` 	 | Get music by performer 	 |

### Actuator Monitoring

| **Method** 	| **Endpoint**       	                    | **Description**   	 |
|------------	|----------------------------------------|-------------------|
| GET        	| `/actuator/health`      	               | App health status |

## GraphQL API Overview

The API also provides a GraphQL endpoint for querying James Bond movie data.

### Endpoint

```text
POST /graphql
```

The GraphQL endpoint requires authentication using the `X-API-Key` header.

```http
X-API-Key: your-api-key
```

GraphQL queries can be sent to:

```text
http://localhost:8080/graphql
```

### Available Queries

#### Movies

| Query | Description |
|---|---|
| `movies` | Returns all movies |
| `movie(id)` | Returns a movie by ID |

Example:

Get all movies

```graphql
query Movies {
    movies {
        id
        movieNumber
        title
        shortDescription
        longDescription
        trailerUrl
        worldPremiere
        contentRating
        jamesBondActor
        locations
        createdAt
        updatedAt
        parentsGuide {
            sexAndNudity
            violenceAndGore
            profanity
            alcoholDrugsAndSmoking
            frighteningAndIntenseScenes
        }
        releaseDates {
            dateOfRelease
            country
            countryCode
        }
        music {
            id
            title
            performer
            songUrl
        }
        genres {
            title
        }
        director {
            id
            name
            biography
            nationality
            dateOfBirth
            dateOfDeath
        }
        producers {
            id
            name
            biography
            nationality
            dateOfBirth
            dateOfDeath
        }
        actors {
            id
            name
            characterRole
            biography
            nationality
            dateOfBirth
            dateOfDeath
        }
        writers {
            id
            name
            dateOfBirth
            dateOfDeath
        }
        trivias {
            content
        }
        boxOffice {
            budgetUsd
            grossRevenueUsAndCanadaUsd
            grossRevenueWorldwideUsd
        }
        technicalSpecifications {
            runtimeInMinutes
            soundMix
            aspectRatio
            printedFilmFormat
        }
    }
}
```

Get a specific movie:

```graphql
query Movie {
    movie(id: "movie-id") {
        id
        movieNumber
        title
        shortDescription
        longDescription
        trailerUrl
        worldPremiere
        contentRating
        jamesBondActor
        locations
        createdAt
        updatedAt
        parentsGuide {
            sexAndNudity
            violenceAndGore
            profanity
            alcoholDrugsAndSmoking
            frighteningAndIntenseScenes
        }
        releaseDates {
            dateOfRelease
            country
            countryCode
        }
        music {
            id
            title
            performer
            songUrl
        }
        genres {
            title
        }
        director {
            id
            name
            biography
            nationality
            dateOfBirth
            dateOfDeath
        }
        producers {
            id
            name
            biography
            nationality
            dateOfBirth
            dateOfDeath
        }
        actors {
            id
            name
            characterRole
            biography
            nationality
            dateOfBirth
            dateOfDeath
        }
        writers {
            id
            name
            dateOfBirth
            dateOfDeath
        }
        trivias {
            content
        }
        boxOffice {
            budgetUsd
            grossRevenueUsAndCanadaUsd
            grossRevenueWorldwideUsd
        }
        technicalSpecifications {
            runtimeInMinutes
            soundMix
            aspectRatio
            printedFilmFormat
        }
    }
}
```

#### Actors

| Query | Description |
|---|---|
| `actors` | Returns all actors |
| `actor(id)` | Returns an actor by ID |
| `actorByName(name)` | Returns actors matching a name |

Example:

Get all actors

```graphql
query Actors {
    actors {
        id
        name
        characterRole
        biography
        nationality
        dateOfBirth
        dateOfDeath
        movies {
            id
            movieNumber
            title
        }
    }
}
```

Get a specific actor by id

```graphql
query Actor {
    actor(id: "actor-id") {
        id
        name
        characterRole
        biography
        nationality
        dateOfBirth
        dateOfDeath
        movies {
            id
            movieNumber
            title
        }
    }
}
```

Get a specific actor by name

```graphql
query ActorByName {
    actorByName(name: "Actor Name") {
        id
        name
        characterRole
        biography
        nationality
        dateOfBirth
        dateOfDeath
        movies {
            id
            movieNumber
            title
        }
    }
}
```

#### Directors

| Query | Description |
|---|---|
| `directors` | Returns all directors |
| `director(id)` | Returns a director by ID |
| `directorByName(name)` | Returns directors matching a name |

Example:

Get all directors

```graphql
query Directors {
    directors {
        id
        name
        biography
        nationality
        dateOfBirth
        dateOfDeath
        movies {
            id
            movieNumber
            title
        }
    }
}
```

Get a specific director by id

```graphql
query Director {
    director(id: "director-id") {
        id
        name
        biography
        nationality
        dateOfBirth
        dateOfDeath
        movies {
            id
            movieNumber
            title
        }
    }
}
```

Get a specific director by name

```graphql
query DirectorByName {
    directorByName(name: "Director Name") {
        id
        name
        biography
        nationality
        dateOfBirth
        dateOfDeath
        movies {
            id
            movieNumber
            title
        }
    }
}
```

#### Producers

| Query | Description |
|---|---|
| `producers` | Returns all producers |
| `producer(id)` | Returns a producer by ID |
| `producerByName(name)` | Returns producers matching a name |

Example:

Get all producers

```graphql
query Producers {
    producers {
        id
        name
        biography
        nationality
        dateOfBirth
        dateOfDeath
        movies {
            id
            movieNumber
            title
        }
    }
}
```

Get a specific producer by id

```graphql
query Producer {
    producer(id: "producer-id") {
        id
        name
        biography
        nationality
        dateOfBirth
        dateOfDeath
        movies {
            id
            movieNumber
            title
        }
    }
}
```

Get a specific producer by name

```graphql
query ProducerByName {
    producerByName(name: "Producer Name") {
        id
        name
        biography
        nationality
        dateOfBirth
        dateOfDeath
        movies {
            id
            movieNumber
            title
        }
    }
}
```

#### Writers

| Query | Description |
|---|---|
| `writers` | Returns all writers |
| `writer(id)` | Returns a writer by ID |
| `writerByName(name)` | Returns writers matching a name |

Example:

Get all Writers

```graphql
query Writers {
    writers {
        id
        name
        dateOfBirth
        dateOfDeath
        movies {
            id
            movieNumber
            title
        }
    }
}
```

Get a specific writer by id

```graphql
query Writer {
    writer(id: "writer-id") {
        id
        name
        dateOfBirth
        dateOfDeath
        movies {
            id
            movieNumber
            title
        }
    }
}
```

Get a specific writer by name

```graphql
query WriterByName {
    writerByName(name: "Writer Name") {
        id
        name
        dateOfBirth
        dateOfDeath
        movies {
            id
            movieNumber
            title
        }
    }
}
```

#### Music

| Query | Description |
|---|---|
| `music` | Returns all music |
| `musicById(id)` | Returns a music entry by ID |
| `musicByPerformer(performer)` | Returns music entries by performer |

Example:

Get all music

```graphql
query Music {
    music {
        id
        title
        performer
        songUrl
    }
}
```

Get music by id

```graphql
query MusicById {
    musicById(id: "music-id") {
        id
        title
        performer
        songUrl
    }
}
```

Get music by performer

```graphql
query MusicByPerformer {
    musicByPerformer(performer: "Performer Name") {
        id
        title
        performer
        songUrl
    }
}
```

---

&copy; 2026 [Martin Rohwedder](https://www.martinrohwedder.dk/)
