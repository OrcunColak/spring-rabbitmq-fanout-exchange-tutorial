package com.colak.springtutorial.service.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class LogStorageConsumer {

    @RabbitListener(queues = "log-storage-queue")
    public void storeLog(String message) {
        // Store log message to a log storage system
        log.info("Log Storage: {}", message);
    }
}
