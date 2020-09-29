# small-cinema
Sample back-end for small cinema mobile/web application

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

### Check API docs
Simply visit the following address:
```
http://localhost:8080/swagger-ui.html
```

