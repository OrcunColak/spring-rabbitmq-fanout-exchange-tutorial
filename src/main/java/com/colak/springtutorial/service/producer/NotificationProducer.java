package com.colak.springtutorial.service.producer;

import com.colak.springtutorial.config.NotificationConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationProducer {

    private final RabbitTemplate rabbitTemplate;

    public void sendNotification(String message) {
        rabbitTemplate.convertAndSend(NotificationConfig.NOTIFICATION_FANOUT_EXCHANGE, "", message);
    }

}
