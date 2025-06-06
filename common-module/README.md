# Java Multi-Module Project with GitHub Packages Integration

This project demonstrates a multi-module Java application that leverages GitHub Packages for dependency management. It consists of a common module that provides greeting functionality and a consumer application that utilizes this common module.

The project showcases the implementation of modular Java development using Gradle, with automated package publishing to GitHub Packages repository. The common module provides reusable functionality that can be shared across multiple applications, while the consumer application demonstrates how to integrate and use the published package.

## Repository Structure
```
.
├── common-module/                 # Shared library module containing common functionality
│   ├── build.gradle              # Gradle build configuration for common module with GitHub Packages publishing
│   └── src/main/java/
│       └── com/example/common/
│           └── GreetingService.java  # Service class providing greeting functionality
└── consumer-app/                 # Application that consumes the common module
    ├── build.gradle             # Gradle build configuration with Spring Boot and common module dependency
    └── src/main/java/
        └── com/example/consumer/
            └── ConsumerApp.java  # Main application entry point
```

## Usage Instructions
### Prerequisites
- Java Development Kit (JDK) 17 or higher
- Gradle 8.9 or higher
- GitHub account with access token for packages
- Environment variables or Gradle properties:
  - `USERNAME`: GitHub username
  - `GITHUB_TOKEN`: GitHub personal access token with packages read/write permissions

### Installation

1. Clone the repository:
```bash
git clone <repository-url>
cd <repository-name>
```

2. Set up GitHub Packages authentication:
```bash
# Option 1: Set environment variables
export USERNAME=your-github-username
export GITHUB_TOKEN=your-github-token

# Option 2: Create gradle.properties in your user's .gradle directory
echo "gpr.user=your-github-username" >> ~/.gradle/gradle.properties
echo "gpr.key=your-github-token" >> ~/.gradle/gradle.properties
```

3. Build and publish the common module:
```bash
cd common-module
./gradlew publish
```

4. Build and run the consumer application:
```bash
cd ../consumer-app
./gradlew bootRun

./gradlew publishMavenJavaPublicationToLocalNexusRepository
```

### Quick Start

1. Create an instance of GreetingService:
```java
import com.example.common.GreetingService;

GreetingService greetingService = new GreetingService();
String greeting = greetingService.greet("World");
System.out.println(greeting); // Outputs: Hello, World!
```

### More Detailed Examples

Using the GreetingService in a Spring Boot application:
```java
import com.example.common.GreetingService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        GreetingService greetingService = new GreetingService();
        System.out.println(greetingService.greet("Spring User"));
    }
}
```

### Troubleshooting

Common Issues:

1. GitHub Packages Authentication Error
```
Error: 401 Unauthorized
```
Solution:
- Verify GitHub token has packages:read and packages:write permissions
- Check if environment variables or gradle.properties are properly set
- Ensure token is not expired

2. Java Version Mismatch
```
Error: java.lang.UnsupportedClassVersionError
```
Solution:
- Verify JDK 17 is installed: `java -version`
- Ensure JAVA_HOME points to JDK 17
- Check project's Java compatibility settings in build.gradle

## Data Flow
The application follows a simple flow where the consumer application utilizes the common module's greeting service to generate personalized greetings.

```ascii
[ConsumerApp] --> [GreetingService] --> [Greeting Output]
     |                  |                     |
     |                  |                     |
Input name -----> Generate greeting ----> Display result
```

Component Interactions:
- ConsumerApp acts as the entry point and dependency consumer
- GreetingService provides the core greeting functionality
- Communication is through direct method calls
- Input validation is handled at the service level
- Synchronous execution flow
- Error handling is managed through Java exceptions
- Thread-safe implementation for concurrent usage



./gradlew clean test jacocoTestReport sonar \
  -Dsonar.projectKey=auth-service \
  -Dsonar.projectName='auth-service' \
  -Dsonar.host.url=http://localhost:9000 \
  -Dsonar.token=fe83680caf66f82235b83d71a53




 ./gradlew sonar \
  -Dsonar.projectKey=auth-service \
  -Dsonar.projectName='auth-service' \
  -Dsonar.host.url=http://localhost:9000 \
  -Dsonar.token=sqp_4c58cab2cf8b7fe83680caf66f82235b83d71a53