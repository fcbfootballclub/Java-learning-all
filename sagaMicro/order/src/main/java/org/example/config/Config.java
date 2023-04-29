package org.example.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class Config {

    @Value("${spring.kafka.topic.name-order}")
    private String topicName;
//    @Bean
//    public Sinks.Many<OrderEvent> orderSinks() {
//        return Sinks.many().multicast().onBackpressureBuffer();
//    }
//
//    public Supplier<Flux<OrderEvent>> orderSupplier(Sinks.Many<OrderEvent> sinks) {
//        return orderSinks()::asFlux;
//    }

    @Bean
    NewTopic oderTopic() {
        return TopicBuilder
                .name(topicName)
                .build();
    }

}
