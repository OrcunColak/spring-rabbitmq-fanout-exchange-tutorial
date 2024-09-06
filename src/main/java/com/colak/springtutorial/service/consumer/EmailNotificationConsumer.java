package com.colak.springtutorial.service.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class EmailNotificationConsumer {

    @RabbitListener(queues = "email-queue")
    public void sendEmailNotification(String message) {
        // Send email notification logic
        log.info("Email Notification: {}", message);
    }
}
