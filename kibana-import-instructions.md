# Kibana Dashboard Import Instructions

Follow these steps to properly import the Spring Boot logs dashboard into Kibana:

## Step 1: Create Index Pattern

1. Go to Kibana → Management → Stack Management → Index Patterns
   (or directly access: http://localhost:5601/app/management/kibana/indexPatterns)

2. Click "Create index pattern"

3. Enter the following:
   - Index pattern name: `springboot-*` 
   - Time field: `@timestamp`

4. Click "Create index pattern"

## Step 2: Import Visualizations

Import the visualizations in this order:

1. Go to Kibana → Management → Stack Management → Saved Objects
   (or directly access: http://localhost:5601/app/management/kibana/objects)

2. Click "Import" and select these files one by one:
   ```
   kibana/dashboards/log-levels-over-time.ndjson
   kibana/dashboards/top-error-sources.ndjson
   kibana/dashboards/recent-logs.ndjson
   kibana/dashboards/log-levels-distribution.ndjson
   ```

3. For each file:
   - When prompted for conflicts, choose "Automatically overwrite all conflicts"
   - For index pattern conflicts, select your `springboot-*` pattern

## Step 3: Import Dashboard

After importing all visualizations:

1. Go back to Saved Objects

2. Click "Import" and select:
   ```
   kibana/dashboards/spring-boot-dashboard.ndjson
   ```

3. When prompted for conflicts, choose "Automatically overwrite all conflicts"

## Step 4: View Dashboard

1. Go to Kibana → Dashboard
   (or directly access: http://localhost:5601/app/dashboards#/list)

2. Find and click on "Spring Boot Logs Dashboard"

## Troubleshooting

If you encounter errors during import:

1. Make sure you've created the index pattern first
2. Import visualizations before importing the dashboard
3. Check that the index pattern name matches exactly (`springboot-*`)
4. Verify that Elasticsearch has some data by running:
   ```
   curl -X GET "localhost:9200/_cat/indices?v"
   ```

If you still have issues, try creating the visualizations manually in Kibana using the same configurations.