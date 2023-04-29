package org.example.service;

import org.example.event.OrderEvent;
import org.example.event.PaymentEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class ProcessPayment {
    @Value("${spring.kafka.topic.name-payment-status}")
    private String topic;

    @Autowired
    KafkaTemplate<String, PaymentEvent> kafkaTemplate;

    @Autowired
    private PaymentService paymentService;

    private static final Logger LOGGER = LoggerFactory.getLogger(ProcessPayment.class);


    @KafkaListener(
            topics = "${spring.kafka.topic.name-order}",
            groupId ="${spring.kafka.consumer.group-id}"
    )
    public void processPayment(OrderEvent orderEvent) {
        LOGGER.info(String.format("Json message recieved -> %s", orderEvent.toString()));
        PaymentEvent paymentEvent = paymentService.processPayment(orderEvent);
        kafkaTemplate.send(topic, paymentEvent);
    }
}
