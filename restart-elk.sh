#!/bin/bash

# Script to restart the ELK stack
echo "=== Restarting ELK Stack ==="

# Stop containers
echo "Stopping containers..."
docker-compose -f docker-compose-logging.yml down

# Remove volumes (optional, uncomment if you want to start fresh)
# echo "Removing volumes..."
# docker volume rm $(docker volume ls -q | grep -E 'elasticsearch-data|filebeat-data')

# Start containers
echo "Starting containers..."
docker-compose -f docker-compose-logging.yml up -d

# Wait for services to start
echo "Waiting for services to start..."
sleep 10

# Check status
echo "Checking container status..."
docker ps | grep -E 'elasticsearch|logstash|kibana|filebeat'

echo -e "\n=== ELK Stack has been restarted ==="
echo "Kibana should be available at: http://localhost:5601"
echo "Elasticsearch should be available at: http://localhost:9200"