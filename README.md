# small-cinema
Sample back-end for small cinema mobile/web application

### Tech stack
- Kotlin 1.3.72 (JDK 11), Gradle, Spring Boot 2.x
- MockK, JUnit
- H2 database
- Docker
#### Tested with
- openjdk version 11.0.8
- Docker version 19.03.2 
- Ubuntu 18.04.5 LTS

### Assumptions / remarks / design choices
1. I've decided to not tightly couple movie <-> review <-> schedule
in the database. I think each of these areas could develop
and constitute separate service in the future.
2. For the same reason I've implemented a "vertical" package layout,
so that movies, reviews and schedule areas are clearly separated.
3. As movie details fetching needs external API call and there are some limits, I've decided to
add some cache upon service responsible for that.
It's never evicted (for now).
4. Usually repository entities are not mapped in the logic layer. 
Review area doesn't even have logic layer as for now I considered it as unnecessary.
5. To fill "internal endpoint" requirement I've implemented
HTTP Basic Auth. I am aware it's not a safe method, but I think it's good enough
for the purpose of this task.
6. I've skipped data validation - it was a conscious decision.
It would need to be added in the next version.
7. I've implemented various types of tests: unit, controllers ITs (deep and flat), repository ITs
8. The API docs is missing some details (i.e. example values).
It would need to be added in the next version.
9. I've added "average movie rating", "all movies" endpoints and simple schedules conflicts from myself.

### Configure
In order to use movie details endpoint, add your OMDBApi key and url template 
to: ``/src/main/resources/application.yml``

Sample config:
```yml
small-cinema:
  omdbApi:
    key: "[your API key]"
    urlTemplate: "http://www.omdbapi.com/?apikey={0}&i={1}"
```
Schedule admin endpoints are secured using HTTP Basic Auth - 
default username and password can be found 
and changed in: ``/src/main/resources/application.yml``

Sample config:
```yml
spring:
  security:
    user:
      name: "admin"
      password: "[your password]"
```
Note: Changing security credentials invalidates authorization token from this README

### Build and run
To build project use:
```
./gradlew build
```
To run service after building the project use:
```
./gradlew bootRun
```

### Build and run as a container
To build service docker use:
```
./gradlew docker
```
To run service after building the docker use:
```
./gradlew dockerRun
```

### Verify if service works
Simply visit the following address:
```
http://localhost:8080/api/v1/health
```
or use cURL:
```
curl http://localhost:8080/api/v1/health
```
In both cases the response should look as follows:
```json
{
  "status": "UP"
}
```

### Check API
Simply visit the following address to access API docs and test it:
```
http://localhost:8080/swagger-ui.html
```

Schedule admin endpoints need an authorization token header, for default config it looks as follows:
```
Authorization: Basic YWRtaW46MTIzc2M0NTY=
```

### Deep dive into database
Open H2 console:
```
http://localhost:8080/h2-console/
```
Default database name, user and password can be found in:
``
/src/main/resources/application.yml
``

Database is populated with some sample data for movies, reviews and schedules.

