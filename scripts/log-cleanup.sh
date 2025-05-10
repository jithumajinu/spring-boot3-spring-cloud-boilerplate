#!/bin/bash

# Script to manually clean up old log files
# Usage: ./log-cleanup.sh [retention_days]

# Default retention period is 30 days if not specified
RETENTION_DAYS=${1:-30}
LOG_DIR="./auth-service/logs"

echo "Cleaning up log files older than $RETENTION_DAYS days in $LOG_DIR"

# Find and delete log files older than retention period
find $LOG_DIR -name "*.log" -type f -mtime +$RETENTION_DAYS -exec rm -f {} \;

echo "Log cleanup completed"