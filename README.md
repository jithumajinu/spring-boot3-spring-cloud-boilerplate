# Spring Boot 3 Boilerplate with Monitoring and Logging

## Starting the Application

```bash
cd auth-service
# Start your Spring Boot application with monitoring enabled:
./gradlew bootRun --args='--spring.profiles.active=monitoring'
```

## Monitoring Setup

### Start Monitoring Stack
```bash
# to stop
docker-compose -f docker-compose-monitoring.yml down
# Start Prometheus, Alertmanager, and Grafana:
docker-compose -f docker-compose-monitoring.yml up -d
```

### Access Monitoring Tools
- **Grafana**: http://localhost:3000 (login with admin/admin)
- **Prometheus**: http://localhost:9090
- **Alertmanager**: http://localhost:9093

## Logging Setup (ELK Stack)

### Start Logging Stack
```bash
# Start Elasticsearch, Logstash, Kibana, and Filebeat:
docker-compose -f docker-compose-logging.yml up -d
```

### Access Logging Tools
- **Kibana**: http://localhost:5601
- **Elasticsearch**: http://localhost:9200

For detailed logging setup instructions, see [README-LOGGING.md](README-LOGGING.md)

## Actuator Endpoints

Access Spring Boot Actuator endpoints:
- http://localhost:8081/actuator
- http://localhost:8081/actuator/health
- http://localhost:8081/actuator/info
- http://localhost:8081/actuator/metrics
- http://localhost:8081/actuator/metrics/system.cpu.usage
- http://localhost:8081/actuator/prometheus

## Available Endpoints

- `/actuator/health`: Shows application health information
- `/actuator/info`: Displays application information
- `/actuator/metrics`: Shows metrics information
- `/actuator/env`: Exposes environment properties
- `/actuator/loggers`: Shows and modifies logger configurations
- `/actuator/prometheus`: Exposes metrics in Prometheus format

## Monitoring Features

1. **Metrics Collection**: Spring Boot Actuator + Prometheus
2. **Visualization**: Grafana dashboards for system and application metrics
3. **Alerting**: Automatic alerts for 500 errors and high error rates
4. **Notifications**: Email and Microsoft Teams integration

## Alert System

The monitoring system includes alerts for:
- HTTP 500 errors
- High error rates
- API-specific errors

For detailed alert setup instructions, see [README-ALERTS.md](README-ALERTS.md)

## Logging Features

1. **Centralized Logging**: ELK stack for log aggregation and visualization
2. **Structured Logging**: JSON format for better parsing and querying
3. **Log Rotation**: Automatic pruning of old logs
4. **Context Enrichment**: Request IDs, session IDs, and more

## Useful Prometheus Queries

### HTTP Request Metrics
```
# Total HTTP Requests
http_server_requests_seconds_count{application="auth-service"}

# Request Rate
rate(http_server_requests_seconds_count{application="auth-service"}[1m])

# 95th Percentile Response Time
histogram_quantile(0.95, sum(rate(http_server_requests_seconds_bucket{application="auth-service"}[5m])) by (le, uri))

# 500 Errors by Endpoint
sum by (uri) (http_server_requests_seconds_count{application="auth-service", status="500"})
```

## Troubleshooting

If you can't access the endpoints, check:

- **Security configuration**: Make sure the security configuration allows access to `/actuator/**` endpoints
- **Port number**: Verify your application is running on the correct port
- **Endpoint exposure**: Confirm that the endpoints are properly exposed in your `application-monitoring.yml` file
- **Application logs**: Check for any errors related to Actuator in your application logs

## References

- [Spring Boot Actuator Documentation](https://docs.spring.io/spring-boot/docs/current/reference/html/actuator.html)
- [Prometheus Documentation](https://prometheus.io/docs/introduction/overview/)
- [Grafana Documentation](https://grafana.com/docs/grafana/latest/)
- [Elasticsearch Documentation](https://www.elastic.co/guide/index.html)
- [Logstash Documentation](https://www.elastic.co/guide/en/logstash/current/index.html)
- [Kibana Documentation](https://www.elastic.co/guide/en/kibana/current/index.html)