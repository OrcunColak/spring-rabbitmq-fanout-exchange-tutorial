package com.colak.springtutorial.controller;

import com.colak.springtutorial.service.producer.NotificationProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/rabbitmq")
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationProducer notificationProducer;

    // http://localhost:8080/api/rabbitmq/sendMessage/Hello
    @GetMapping("/sendMessage/{message}")
    public ResponseEntity<String> testRabbitmq(@PathVariable String message) {
        // Send email notification
        notificationProducer.sendEmailNotification("New order placed.");

        // Send SMS notification
        notificationProducer.sendSMSNotification("Payment successful.");

        // Store log message
        notificationProducer.sendLogToStorage("Application error occurred.");

        return ResponseEntity.status(HttpStatus.OK).body("Success");
    }
}
