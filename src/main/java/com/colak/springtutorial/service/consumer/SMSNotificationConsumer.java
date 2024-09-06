package com.colak.springtutorial.service.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class SMSNotificationConsumer {

    @RabbitListener(queues = "sms-queue")
    public void sendSMSNotification(String message) {
        // Send SMS notification logic
        log.info("SMS Notification: {}", message);
    }
}
