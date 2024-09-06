package com.colak.springtutorial.service.producer;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.system.CapturedOutput;
import org.springframework.boot.test.system.OutputCaptureExtension;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.RabbitMQContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.awaitility.Awaitility.await;

@SpringBootTest
@Testcontainers
@ExtendWith(OutputCaptureExtension.class)
class RabbitMQProducerIT {


    @Container
    @ServiceConnection
    public static final RabbitMQContainer rabbitMQContainer = new RabbitMQContainer("rabbitmq:3.8-management-alpine");

    @Autowired
    private NotificationProducer notificationProducer;

    // This test captures the log output of the consumer
    @Test
    void testSendMessage(CapturedOutput output) {
        assertThatCode(() -> notificationProducer.sendEmailNotification("12345")).doesNotThrowAnyException();
        await().atMost(10, TimeUnit.SECONDS)
                .until(isMessageConsumed(output));
    }

    private Callable<Boolean> isMessageConsumed(CapturedOutput output) {
        return () -> {
            String out = output.getOut();
            return out.contains("Email Notification: Email: 12345")
                   && out.contains("SMS Notification: Email: 12345")
                   && out.contains("Log Storage: Email: 12345");
        };
    }

}
