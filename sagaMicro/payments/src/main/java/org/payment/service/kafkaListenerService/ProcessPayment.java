package org.payment.service.kafkaListenerService;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.core.enumPac.CONSTANT;
import org.core.dto.OrderResponseDto;
import org.core.entity.PaymentEventOutBox;
import org.core.event.PaymentEvent;
import org.payment.repository.PaymentOutBoxRepository;
import org.payment.service.Imp.PaymentService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class ProcessPayment {
    @Autowired
    KafkaTemplate<String, PaymentEvent> kafkaTemplate;

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private Logger logger;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private PaymentOutBoxRepository paymentOutBoxRepository;

    @KafkaListener(
            topics = "${spring.kafka.topic.payment-request}",
            groupId ="${spring.kafka.consumer.group-id}"
    )
    @Transactional
    public void processPayment(OrderResponseDto orderResponseDto) {
        logger.info(String.format("Json message recieved -> %s", orderResponseDto.toString()));
        PaymentEvent paymentEvent = paymentService.processPayment(orderResponseDto);
        PaymentEventOutBox event;
        try {
            event = PaymentEventOutBox.builder()
                    .id(orderResponseDto.getOrderId())
                    .topicName(CONSTANT.TOPIC.INVENTORY_REQUEST.toString())
                    .eventJson(objectMapper.writeValueAsString(paymentEvent))
                    .publicStatus(false)
                    .build();
        } catch (JsonProcessingException e) {
            logger.info("can not cast payment event to string");
            throw new RuntimeException("create payment event outbox exception");
        }
        paymentOutBoxRepository.save(event);
        logger.info("save event succcsesfully " + paymentEvent.toString());
    }
}
