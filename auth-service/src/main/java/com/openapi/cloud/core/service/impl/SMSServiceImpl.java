package com.openapi.cloud.core.service.impl;

import com.openapi.cloud.core.constants.NotificationType;
import com.openapi.cloud.core.service.NotificationService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("smsNotification")
public class SMSServiceImpl implements NotificationService {

    @Override
    public void sendNotification(String to, String subject, String message) {
        try {

            System.out.println("Send SMS");

        } catch (Exception e) {

        }
    }

    @Override
    public boolean validateRecipient(String phoneNumber) {
        String phoneRegex = "^\\+?[0-9]\\d{1,14}$";
        return phoneNumber != null && phoneNumber.matches(phoneRegex);
    }

    @Override
    public NotificationType getType() {
        return NotificationType.SMS;
    }
}
