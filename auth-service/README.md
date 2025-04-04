# Config-server

Run: `./gradlew bootRun --stacktrace`
Run: `./gradlew clean bootRun`
Run: `./gradlew clean`
Run: `./gradlew bootRun`

 ./gradlew clean build

#### Check profile

http://localhost:1006/client

http://localhost:1006/message


Windows
gradlew.bat build
gradlew.bat clean
gradlew.bat bootRun
gradlew.bat --version

Optional
./gradlew bootRun --args='--spring.profiles.active=dev'


#### Check profile

http://localhost:1006/cloud_config/message

http://localhost:1006/cloud_config/client



## URLS
```
* Swagger UI:  http://localhost:1006/swagger-ui/index.html
* Browser: http://localhost:1006/
* Curl:  curl -i http://localhost:1006/