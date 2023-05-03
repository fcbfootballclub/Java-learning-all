package org.example.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class Config {

    @Value("${spring.kafka.topic.name-inventory-request}")
    private String topic;

    @Bean
    public NewTopic paymentStatus() {
        return TopicBuilder
                .name(topic)
                .build();
    }
}
