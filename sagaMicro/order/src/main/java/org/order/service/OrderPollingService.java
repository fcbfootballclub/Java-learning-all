package org.order.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.core.dto.OrderResponseDto;
import org.core.entity.OrderEventOutBox;
import org.order.repository.OrderEventOutBoxRepository;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaProducerException;
import org.springframework.kafka.core.KafkaSendCallback;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class OrderPollingService {
    @Value("${spring.kafka.topic.name-payment-request}")
    String topicName;

    @Autowired
    private OrderEventOutBoxRepository orderEventOutBoxRepository;

    @Autowired
    private KafkaTemplate<String, OrderResponseDto> kafkaTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private Logger logger;

    @Scheduled(fixedDelay = 5000)
    public void publishOrderEvent() {
        List<OrderEventOutBox> listEvent = orderEventOutBoxRepository.findAllByPublicStatus(false);
        if(listEvent.size() != 0) {
            for(OrderEventOutBox orderEventOutBox : listEvent) {
                OrderResponseDto orderResponseDto;
                try {
                    orderResponseDto = objectMapper.readValue(orderEventOutBox.getEventJson(), OrderResponseDto.class);
                    kafkaTemplate.send(topicName, orderResponseDto.getOrderId().toString(), orderResponseDto)
                            .addCallback(new KafkaSendCallback<>() {
                                @Override
                                public void onSuccess(SendResult<String, OrderResponseDto> result) {
                                    logger.info("send event successfully!");
                                    orderEventOutBox.setPublicStatus(true);
                                    orderEventOutBoxRepository.save(orderEventOutBox);
                                }
                                @Override
                                public void onFailure(KafkaProducerException e) {
                                    logger.error("send event error: " + orderResponseDto.toString());
                                }
                            });
                } catch (JsonProcessingException e) {
                    logger.error("can not convert order event to event " + e);
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
