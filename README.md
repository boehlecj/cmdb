# Chuck Movie Database
This is an in memory movie database using Spring Boot

## Implementation Details
```
The service uses the Spring Data CrudRepository instead of implementing a DAO pattern.
From start to finish this took 1 hour.
Includes Unit Tests
```

## Service Route
```
/api/timeOfDay	Returns the current local time
/api/movie[/*]	Create, update, delete movie entry
/api/movie/list	Returns list of movie entries
```

## Docker
```
There is a DockerFile included in the project but due to my system resources issue rung the docker toolkit I could not test build an iso.
I spent a lot of time on this project just troubleshooting my own docker toolkit issues.
```
