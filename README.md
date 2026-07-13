[![Java CI with Maven](https://github.com/martin-rohwedder/james-bond-movies-api/actions/workflows/maven.yml/badge.svg)](https://github.com/martin-rohwedder/james-bond-movies-api/actions/workflows/maven.yml)

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
```

3. Make sure you have [Docker Desktop](https://docs.docker.com/desktop/) installed and running on your machine.
4. Run the project and it will automatically open up in a new docker container with an attached MySQL database.