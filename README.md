[![Java CI with Maven](https://github.com/martin-rohwedder/james-bond-movies-api/actions/workflows/maven.yml/badge.svg)](https://github.com/martin-rohwedder/james-bond-movies-api/actions/workflows/maven.yml)
![Coverage](.github/badges/jacoco.svg)
![Java](https://img.shields.io/badge/Java-25-d15e5c?logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-4.1.0-6DB33F?logo=springboot&logoColor=6DB33F)
![MySQL](https://img.shields.io/badge/MySQL-8.0-4479A1?logo=mysql&logoColor=a5cae6)

# James Bond Movies API

An API for all the James Bond movies.

## How to get started

1. Clone the project and open it up in your favorite coding IDE (e.g. [IntelliJ](https://www.jetbrains.com/idea/)).
2. Create an `.env` (environment file) at the project root and insert the following environments.

```dotenv
MYSQL_DATABASE=mydatabase
MYSQL_PASSWORD=secret
MYSQL_ROOT_PASSWORD=verysecret
MYSQL_USER=myuser

API_KEY=your-very-long-random-api-key
```

3. Make sure you have [Docker Desktop](https://docs.docker.com/desktop/) installed and running on your machine.
4. Run the project and it will automatically open up in a new docker container with an attached MySQL database.

# API Documentation

When the API is running locally, interactive API documentation is available through Swagger UI.

- Swagger UI: http://localhost:8080/swagger-ui/index.html
- OpenAPI JSON: http://localhost:8080/v3/api-docs

Swagger UI allows you to explore the available endpoints and execute requests directly from the browser.

## Authentication

Since API is protected with an API key (set in your `.env` file), you should include the API key in the request header `X-API-Key`.

In the Swagger UI, click **Authorize** and enter your API key to authenticate requests.

---

&copy; 2026 Martin Rohwedder
