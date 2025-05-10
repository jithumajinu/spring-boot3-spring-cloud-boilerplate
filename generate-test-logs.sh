#!/bin/bash

# Script to generate test log entries for ELK stack testing
# This creates JSON-formatted log entries that can be processed by Logstash

LOG_DIR="./auth-service/logs"
LOG_FILE="$LOG_DIR/test-logs.json"

# Create logs directory if it doesn't exist
mkdir -p $LOG_DIR

echo "Generating test logs in $LOG_FILE..."

# Generate current timestamp in ISO format
TIMESTAMP=$(date -u +"%Y-%m-%dT%H:%M:%S.000Z")

# Create the log file with test entries
cat > $LOG_FILE << EOF
{"@timestamp":"$TIMESTAMP","level":"INFO","thread":"main","logger":"com.openapi.cloud.Application","message":"Application started successfully"}
{"@timestamp":"$(date -u -v-1M +"%Y-%m-%dT%H:%M:%S.000Z")","level":"DEBUG","thread":"http-nio-8081-exec-1","logger":"com.openapi.cloud.core.controller.AuthController","message":"Processing login request"}
{"@timestamp":"$(date -u -v-2M +"%Y-%m-%dT%H:%M:%S.000Z")","level":"INFO","thread":"http-nio-8081-exec-2","logger":"com.openapi.cloud.core.service.UserService","message":"User authenticated successfully"}
{"@timestamp":"$(date -u -v-3M +"%Y-%m-%dT%H:%M:%S.000Z")","level":"WARN","thread":"http-nio-8081-exec-3","logger":"com.openapi.cloud.core.security.JwtAuthenticationFilter","message":"Token expiration approaching"}
{"@timestamp":"$(date -u -v-4M +"%Y-%m-%dT%H:%M:%S.000Z")","level":"ERROR","thread":"http-nio-8081-exec-4","logger":"com.openapi.cloud.core.exception.GlobalExceptionHandler","message":"Failed to process request: Invalid input"}
{"@timestamp":"$(date -u -v-5M +"%Y-%m-%dT%H:%M:%S.000Z")","level":"INFO","thread":"http-nio-8081-exec-5","logger":"com.openapi.cloud.core.controller.CustomerController","message":"Retrieved customer data"}
{"@timestamp":"$(date -u -v-6M +"%Y-%m-%dT%H:%M:%S.000Z")","level":"DEBUG","thread":"http-nio-8081-exec-6","logger":"com.openapi.cloud.core.repository.CustomerRepository","message":"Executing query: findAllByStatus"}
{"@timestamp":"$(date -u -v-7M +"%Y-%m-%dT%H:%M:%S.000Z")","level":"INFO","thread":"http-nio-8081-exec-7","logger":"com.openapi.cloud.core.service.ProductService","message":"Product created with ID: 12345"}
{"@timestamp":"$(date -u -v-8M +"%Y-%m-%dT%H:%M:%S.000Z")","level":"WARN","thread":"background-prune-1","logger":"com.openapi.cloud.core.service.CacheService","message":"Cache size exceeds threshold, pruning old entries"}
{"@timestamp":"$(date -u -v-9M +"%Y-%m-%dT%H:%M:%S.000Z")","level":"ERROR","thread":"http-nio-8081-exec-8","logger":"com.openapi.cloud.core.service.DatabaseService","message":"Database connection timeout"}
EOF

echo "Generated 10 test log entries with different levels and timestamps"
echo "You can now check Kibana to see if these logs are being indexed"