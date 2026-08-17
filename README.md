[![Java CI with Maven](https://github.com/martin-rohwedder/james-bond-movies-api/actions/workflows/maven.yml/badge.svg)](https://github.com/martin-rohwedder/james-bond-movies-api/actions/workflows/maven.yml)
![Coverage](.github/badges/jacoco.svg)
![Java](https://img.shields.io/badge/Java-25-d15e5c?logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-4.1.0-6DB33F?logo=springboot&logoColor=6DB33F)
![MySQL](https://img.shields.io/badge/MySQL-8.0-4479A1?logo=mysql&logoColor=a5cae6)

# James Bond Movies API

REST API built with Spring Boot for James Bond movies, directors, producers, actors, and music.

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
    image: ghcr.io/martin-rohwedder/james-bond-movies-api:1.2.0
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

## Endpoint Overview

An overview of all endpoints

### Movies

| **Method** 	| **Endpoint**       	| **Description**   	|
|------------	|--------------------	|-------------------	|
| GET        	| `/api/movies`      	| List all movies   	|
| GET        	| `/api/movies/{id}` 	| Get a movie by id 	|

### Actors

| **Method** 	| **Endpoint**       	                                        | **Description**   	                     |
|------------	|------------------------------------------------------------|----------------------------------------|
| GET        	| `/api/actors`      	                                        | List all actors   	                     |
| GET        	| `/api/actors/{id}` 	                                        | Get an actor by id 	                    |
| GET        	| `/api/actors?name={actor_name}`	                            | Get an actor by name	                   |
| GET        	| `/api/actors?name={actor_name}&includeMovies={true/false}`	 | Get an actor by name	and include movies |

### Directors

| **Method** 	| **Endpoint**       	                    | **Description**   	      |
|------------	|----------------------------------------|-------------------------|
| GET        	| `/api/directors`      	                 | List all directors   	   |
| GET        	| `/api/directors/{id}` 	                 | Get a director by id 	   |
| GET        	| `/api/directors?name={director_name}` 	 | Get a director by name 	 |

### Producers

| **Method** 	| **Endpoint**       	                    | **Description**   	      |
|------------	|----------------------------------------|-------------------------|
| GET        	| `/api/producers`      	                 | List all producers   	   |
| GET        	| `/api/prodcuers/{id}` 	                 | Get a producer by id 	   |
| GET        	| `/api/producers?name={producer_name}` 	 | Get a producer by name 	 |

### Actuator Monitoring

| **Method** 	| **Endpoint**       	                    | **Description**   	 |
|------------	|----------------------------------------|-------------------|
| GET        	| `/actuator/health`      	               | App health status |

---

&copy; 2026 Martin Rohwedder
