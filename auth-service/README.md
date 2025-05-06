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

## login creds

{
"name": "jithumajinu",
"username": "jithu.majinu",
"email": "jithu.majinu@gmail.com",
"password": "jithutest"
}

## URLS

```
* Swagger UI:  http://localhost:1006/swagger-ui/index.html
* Browser: http://localhost:1006/
* Curl:  curl -i http://localhost:1006/


response include error

lession 14

Locale settings
Open Chrome settings → Language → Preferred languages.
Add 日本語 (Japanese) and move it to top.
--------
Or use Chrome Developer Tools:
Open DevTools → Network tab → Right-click the request → "Copy as cURL"
You'll see Accept-Language: ja or Accept-Language: en-US,en;q=0.9 etc

Normal readable text to Unicode escaped format (use .properties files, especially for non-ASCII languages (like Japanese, Chinese))
You can use the native2ascii tool, which comes with the JDK.
native2ascii input.txt output.txt

echo "権限エラー" > input.txt
native2ascii input.txt output.txt
cat output.txt

   OR

  Online Unicode Converter https://www.branah.com/unicode-converter



// In your @Slf4j annotated class
log.error("Error message");
log.warn("Warning message");
log.info("Info message");
log.debug("Debug message");
log.trace("Trace message");








