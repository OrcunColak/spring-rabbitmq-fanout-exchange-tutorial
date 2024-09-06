package com.colak.springtutorial.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class NotificationConfig {

    public static final String NOTIFICATION_FANOUT_EXCHANGE = "notification-fanout-exchange";

    @Bean
    public FanoutExchange notificationFanoutExchange() {
        return new FanoutExchange(NOTIFICATION_FANOUT_EXCHANGE);
    }

    @Bean
    public Queue emailQueue() {
        return new Queue("email-queue");
    }

    @Bean
    public Queue smsQueue() {
        return new Queue("sms-queue");
    }

    @Bean
    public Queue logStorageQueue() {
        return new Queue("log-storage-queue");
    }

    @Bean
    public Binding emailBinding() {
        return BindingBuilder.bind(emailQueue()).to(notificationFanoutExchange());
    }

    @Bean
    public Binding smsBinding() {
        return BindingBuilder.bind(smsQueue()).to(notificationFanoutExchange());
    }

    @Bean
    public Binding logStorageBinding() {
        return BindingBuilder.bind(logStorageQueue()).to(notificationFanoutExchange());
    }
}
