package com.colak.springtutorial.service.producer;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationProducer {

    private final RabbitTemplate rabbitTemplate;

    private final FanoutExchange notificationFanoutExchange;

    public void sendNotification(String message) {
        rabbitTemplate.convertAndSend(notificationFanoutExchange.getName(), "", message);
    }

}
