# Movies API

## Description

Spring REST API where you can explore and save your favorite movies :movie_camera:. 

## Technologies and tools used

- [Java](https://www.java.com/en/)
- [Spring Boot](https://spring.io/projects/spring-boot)
- [Spring Data](https://spring.io/projects/spring-data)
- [Spring Security](https://spring.io/projects/spring-security)
- [JUnit5](https://junit.org/junit5/)
- [JWT](https://jwt.io/)
- [PostgreSQL](https://www.postgresql.org/)
- [Swagger](https://swagger.io/)
- [Docker](https://docker.com)
- [Heroku](https://heroku.com)

## Getting started :watermelon:

Clone this repository

```shell
# SSH
$ git clone git@github.com:Jonas56/movies-api.git

# HHTPS
$ git clone https://github.com/Jonas56/movies-api.git
```

## Running the app :dart:

```shell
# using docker
$ docker-compose up -d

# otherwise
# configure your db in application.yml file
$ mvn spring-boot:run
```

## Test :test_tube:

```shell
# integration tests
$ mvn test
```

## Available endpoints

- Visit this [link](https://spring-movies-app.herokuapp.com/swagger-ui/index.html) for more information.