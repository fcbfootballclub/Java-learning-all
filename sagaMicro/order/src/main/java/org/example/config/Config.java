package org.example.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class Config {

    @Value("${spring.kafka.topic.name-payment-request}")
    private String topicName;

    @Bean
    NewTopic oderTopic() {
        return TopicBuilder
                .name(topicName)
                .partitions(3)
                .build();
    }

}
