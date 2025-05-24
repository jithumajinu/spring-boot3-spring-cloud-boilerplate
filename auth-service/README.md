## How to Start Monitoring

1. **Start your Spring Boot application**

   ```bash
   # For auth-service
   cd auth-service
   ./gradlew bootRun --args='--spring.profiles.active=monitoring'

     Options 
    ./gradlew bootRun --stacktrace
    ./gradlew clean bootRun
    ./gradlew clean
    ./gradlew clean build
   ```

2. **Open API (Swagger UI)**
Access application Actuator endpoints:
   - http://localhost:1007/swagger-ui/index.html
   - curl -i http://localhost:1007/


3. **Locale settings (i18)**

   ```bash
   # Open Chrome settings → Language → Preferred languages.Add 日本語 (Japanese) and move it to top.

   OR use Chrome Developer Tools

   #Open DevTools → Network tab → Right-click the request → "Copy as cURL". You'll see Accept-Language: ja or Accept-Language: en-US,en;q=0.9 etc 

   ```  

4. **h2-database console**

   ```bash
   # default path: h2-console  : http://localhost:1006/h2-ui/

   #JDBC URL: jdbc:h2:file:./YOUR_DB_NAME

   ```
