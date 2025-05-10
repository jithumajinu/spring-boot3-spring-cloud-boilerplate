#!/bin/bash

# Script to troubleshoot ELK stack connectivity issues
echo "=== ELK Stack Troubleshooting ==="

# Check if containers are running
echo -e "\n=== Checking container status ==="
docker ps | grep -E 'elasticsearch|logstash|kibana|filebeat'

# Check Elasticsearch status
echo -e "\n=== Checking Elasticsearch status ==="
curl -s -X GET "http://localhost:9200/_cluster/health?pretty" || echo "Failed to connect to Elasticsearch"

# Check Kibana status
echo -e "\n=== Checking Kibana status ==="
curl -s -I "http://localhost:5601" || echo "Failed to connect to Kibana"

# Check container logs
echo -e "\n=== Elasticsearch logs (last 10 lines) ==="
docker logs elasticsearch --tail 10

echo -e "\n=== Kibana logs (last 10 lines) ==="
docker logs kibana --tail 10

echo -e "\n=== Logstash logs (last 10 lines) ==="
docker logs logstash --tail 10

# Check network
echo -e "\n=== Checking Docker network ==="
docker network inspect logging-network

# Check port bindings
echo -e "\n=== Checking port bindings ==="
docker port elasticsearch
docker port kibana
docker port logstash

echo -e "\n=== Troubleshooting complete ==="
echo "If Kibana is still not accessible, try restarting the containers:"
echo "docker-compose -f docker-compose-logging.yml restart"