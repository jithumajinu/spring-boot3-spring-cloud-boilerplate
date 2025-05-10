# Setting Up Alert Notifications for 500 Errors

This guide explains how to configure and use the alert system for 500 errors in your Spring Boot application.

## Alert System Overview

The monitoring system is configured to detect and alert on HTTP 500 errors using:

1. **Prometheus**: Monitors metrics and evaluates alert rules
2. **Alertmanager**: Routes and manages alert notifications
3. **Email/Microsoft Teams**: Notification channels

## Alert Rules

The system includes the following alert rules:

1. **HTTP500Errors**: Triggers when any 500 error occurs in the last 5 minutes
2. **HighErrorRate**: Triggers when the error rate exceeds 5% for 2 minutes
3. **APISpecific500Errors**: Triggers for specific API endpoints returning 500 errors

## Configuration Files

- `prometheus/rules/alert_rules.yml`: Contains the alert rules
- `alertmanager/alertmanager.yml`: Configures notification routing
- `alertmanager/template/email.tmpl`: Email template for notifications

## Setting Up Email Notifications

To configure email notifications:

1. Edit `alertmanager/alertmanager.yml` and update the SMTP settings:

   ```yaml
   global:
     smtp_smarthost: 'your-smtp-server:587'
     smtp_from: 'your-sender-email@example.com'
     smtp_auth_username: 'your-username'
     smtp_auth_password: 'your-password'
     smtp_require_tls: true
   ```

2. Update the recipient email:

   ```yaml
   receivers:
   - name: 'email-notifications'
     email_configs:
     - to: 'your-recipient-email@example.com'
   ```

## Setting Up Microsoft Teams Notifications

To configure Microsoft Teams notifications:

1. In Microsoft Teams, create a webhook:
   - Go to the channel where you want to receive alerts
   - Click the "..." menu → "Connectors"
   - Select "Incoming Webhook" → "Configure"
   - Name it "Prometheus Alerts" and create
   - Copy the webhook URL

2. Update the webhook URL in `docker-compose-monitoring.yml`:

   ```yaml
   alertmanager-webhook-teams:
     environment:
       TEAMS_INCOMING_WEBHOOK_URL: "https://outlook.office.com/webhook/YOUR-TEAMS-WEBHOOK-URL"
   ```

## Testing Alerts

To test if your alerts are working:

1. Generate some 500 errors in your application
2. Check the Prometheus Alerts page at http://localhost:9090/alerts
3. You should see your alert in "pending" or "firing" state
4. After the alert fires, you should receive a notification

## Customizing Alerts

To customize the alert rules:

1. Edit `prometheus/rules/alert_rules.yml`
2. Adjust the expressions, thresholds, or timing
3. Restart Prometheus: `docker-compose -f docker-compose-monitoring.yml restart prometheus`

## Silencing Alerts

To temporarily silence alerts:

1. Go to Alertmanager UI at http://localhost:9093
2. Click "Silences" → "New Silence"
3. Configure the silence duration and matching criteria
4. Click "Create"

## Troubleshooting

If alerts are not working:

1. Check Prometheus UI (http://localhost:9090/alerts) to see if alerts are firing
2. Check Alertmanager UI (http://localhost:9093) to see if alerts are being received
3. Check the logs for Prometheus and Alertmanager:
   ```
   docker-compose -f docker-compose-monitoring.yml logs prometheus
   docker-compose -f docker-compose-monitoring.yml logs alertmanager
   ```
4. Verify your SMTP or Teams webhook settings