# Spring Boot Logging with ELK Stack

This guide explains how to use the ELK (Elasticsearch, Logstash, Kibana) stack to manage, prune, and visualize logs from your Spring Boot application.

## Overview

The logging setup consists of:

1. **Spring Boot Application**: Generates logs in JSON format using Logback
2. **Filebeat**: Collects log files and forwards them to Logstash
3. **Logstash**: Processes and transforms logs
4. **Elasticsearch**: Stores and indexes logs
5. **Kibana**: Visualizes logs in dashboards

## Getting Started

### 1. Start the ELK Stack

```bash
docker-compose -f docker-compose-logging.yml up -d
```

This will start Elasticsearch, Logstash, Kibana, and Filebeat.

### 2. Access Kibana

Open your browser and navigate to:
```
http://localhost:5601
```

### 3. Import Dashboards

1. Go to Kibana → Management → Stack Management → Saved Objects
2. Click "Import" and select the file:
   ```
   kibana/dashboards/spring-boot-logs-dashboard.ndjson
   ```
3. Click "Import" to load the pre-configured dashboards

## Log Configuration

### Logback Configuration

The application uses Logback with the following appenders:

1. **Console Appender**: Outputs logs to the console
2. **RollingFile Appender**: Writes logs to files with daily rotation
3. **ErrorFile Appender**: Captures only ERROR level logs
4. **JSON Appender**: Formats logs as JSON for ELK

### Log Pruning

Logs are automatically pruned based on:

- **Time-based retention**: Keeps logs for 30 days
- **Size-based retention**: Limits total log size to 3GB

## Available Dashboards

1. **Spring Boot Logs Dashboard**: Overview of all application logs
   - Log Levels Over Time: Visualizes log volume by level
   - Top Error Sources: Shows classes generating the most errors
   - Recent Logs: Displays the most recent log entries

## Customizing Log Collection

### Adding Custom Fields

To add custom fields to your logs:

1. Use SLF4J's MDC (Mapped Diagnostic Context) in your code:

```java
import org.slf4j.MDC;

// Add context to your logs
MDC.put("userId", user.getId());
MDC.put("requestId", requestId);

// Log with context
logger.info("User logged in");

// Clear context when done
MDC.clear();
```

2. Update the Logstash encoder in `logback-spring.xml` to include your fields:

```xml
<encoder class="net.logstash.logback.encoder.LogstashEncoder">
    <includeMdcKeyName>userId</includeMdcKeyName>
    <includeMdcKeyName>requestId</includeMdcKeyName>
    <!-- Add more fields as needed -->
</encoder>
```

## Troubleshooting

### No Logs in Elasticsearch

1. Check if your application is generating logs:
   ```
   ls -la auth-service/logs/
   ```

2. Check Filebeat status:
   ```
   docker-compose -f docker-compose-logging.yml logs filebeat
   ```

3. Check Logstash status:
   ```
   docker-compose -f docker-compose-logging.yml logs logstash
   ```

4. Verify Elasticsearch is receiving data:
   ```
   curl -X GET "localhost:9200/_cat/indices?v"
   ```

### Kibana Can't Connect to Elasticsearch

1. Check if Elasticsearch is running:
   ```
   curl -X GET "localhost:9200"
   ```

2. Restart Kibana:
   ```
   docker-compose -f docker-compose-logging.yml restart kibana
   ```

## Advanced Configuration

### Scaling Elasticsearch

For production environments with high log volume:

1. Update `elasticsearch/config/elasticsearch.yml` with cluster settings
2. Add more Elasticsearch nodes to the Docker Compose file
3. Configure sharding and replication for indices

### Securing the ELK Stack

For production environments:

1. Enable X-Pack security in Elasticsearch and Kibana
2. Configure TLS/SSL for all components
3. Set up authentication for Elasticsearch and Kibana
4. Use secure passwords instead of defaults