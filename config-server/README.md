# Config-server

Run: `./gradlew bootRun`

#### Check profile

* `dev` : http://localhost:8888/application/dev

```markdown
{
  "name": "application",
  "profiles": [
    "dev"
  ],
  "label": null,
  "version": "0ee82c6f301d6e7e09cb4da7096f9218ef564cca",
  "state": "",
  "propertySources": [
    {
      "name": "https://github.com/jithumajinu/config-server-spring-boot/config-repo/application-dev.yml",
      "source": {
        "server.port": 9999,
        "spring.application.name": "config-repo",
        "my.greeting": "helloworld from config server"
      }
    },
    {
      "name": "https://github.com/jithumajinu/config-server-spring-boot/config-repo/application.yml",
      "source": {
        "my.greeting.message": "helloworld from config server"
      }
    
```

* `stg` : http://localhost:8888/application/stg

```markdown

{
  "name": "application",
  "profiles": [
    "stg"
  ],
  "label": null,
  "version": "0ee82c6f301d6e7e09cb4da7096f9218ef564cca",
  "state": "",
  "propertySources": [
    {
      "name": "https://github.com/jithumajinu/config-server-spring-boot/config-repo/application.yml",
      "source": {
        "my.greeting.message": "helloworld from config server"
      }
    }
  ]
}

```