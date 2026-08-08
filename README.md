[![Java CI with Maven](https://github.com/martin-rohwedder/james-bond-movies-api/actions/workflows/maven.yml/badge.svg)](https://github.com/martin-rohwedder/james-bond-movies-api/actions/workflows/maven.yml)
![Coverage](.github/badges/jacoco.svg)
![Java](https://img.shields.io/badge/Java-25-d15e5c?logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-4.1.0-6DB33F?logo=springboot&logoColor=6DB33F)
![MySQL](https://img.shields.io/badge/MySQL-8.0-4479A1?logo=mysql&logoColor=a5cae6)

# James Bond Movies API

REST API built with Spring Boot for James Bond movies, directors, producers, actors, and music.

## How to get started

1. Clone the project with `git clone https://github.com/martin-rohwedder/james-bond-movies-api.git` and open it up in your favorite coding IDE (e.g. [IntelliJ](https://www.jetbrains.com/idea/)).
2. Create an `.env` (environment file) at the project root.

```dotenv
MYSQL_DATABASE=mydatabase
MYSQL_USER=myuser
MYSQL_PASSWORD=secret
MYSQL_ROOT_PASSWORD=verysecret

API_KEY=your-very-long-random-api-key
```

3. Replace the `MYSQL` values with your preferred database credentials.
4. Replace the `API_KEY` with a secure random value.
5. Make sure you have [Docker Desktop](https://docs.docker.com/desktop/) installed and running on your machine.
6. Build the application JAR file with `.mvnw clean package` (or on Windows `mvnw.cmd clean package`)

### Start the containers

Use `docker compose --profile deploy up --build -d`.

This starts:
- **MySQL** on port `3306`
- **API** on port `8080`

### Stop the containers

Use `docker compose --profile deploy down`

If you need to remove the MySQL volume as well use `docker compose --profile deploy down -v`

## API Documentation

When the API is running locally, interactive API documentation is available through Swagger UI.

- Swagger UI: http://localhost:8080/swagger-ui/index.html
- OpenAPI JSON: http://localhost:8080/v3/api-docs

Swagger UI allows you to explore the available endpoints and execute requests directly from the browser.

### Authentication

Since API is protected with an API key (set in your `.env` file), you should include the API key in the request header `X-API-Key`.

In the Swagger UI, click **Authorize** and enter your API key to authenticate requests.

*Example request:*

```bash
GET /api/movies HTTP/1.1
Host: localhost:8080
X-API-Key: your-very-long-random-api-key
```

*Example with curl:*

```bash
curl -H "X-API-Key: your-very-long-random-api-key" \
http://localhost:8080/api/movies
```

---

&copy; 2026 Martin Rohwedder
