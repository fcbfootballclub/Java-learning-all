package org.example.service.kafkaListenerService;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.core.enumPac.CONSTANT;
import org.core.enumPac.PaymentStatus;
import org.example.entity.OutBox.InventoryOutBox;
import org.core.event.PaymentEvent;
import org.example.repository.InventoryOutBoxRepository;
import org.example.service.ProductService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class KafkaInventoryService {
    @Autowired
    private Logger logger;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private ProductService productService;
    @Autowired
    private InventoryOutBoxRepository inventoryOutBoxRepository;

    @KafkaListener(
            topics = "${spring.kafka.topic.name-inventory-request}",
            groupId = "${spring.kafka.consumer.group-id}"
    )
    @Transactional
    public void deductQuantity(PaymentEvent paymentEvent) {
        logger.info(String.format("Json message recieved in product -> %s", paymentEvent.toString()));
        InventoryOutBox inventory;
        inventory = InventoryOutBox.builder()
                .id(paymentEvent.getPaymentRequestDto().getOrderId())
                .publicStatus(false)
                .build();
        boolean check = productService.reduceQuantity(paymentEvent.getOrderResponseDto().getProductId(), paymentEvent.getOrderResponseDto().getQuantity());
        if (check) {
            inventory.setTopicName(CONSTANT.TOPIC.ORDER_COMPLETED_REQUEST.toString());
            paymentEvent.setPaymentStatus(PaymentStatus.PAYMENT_COMPLETED);
            try {
                inventory.setEventJson(objectMapper.writeValueAsString(paymentEvent.getOrderResponseDto()));
            } catch (JsonProcessingException e) {
                logger.info("cast object exception");
                throw new RuntimeException(e);
            }
        } else {
            inventory.setTopicName(CONSTANT.TOPIC.INVENTORY_FAILED.toString());
            paymentEvent.setPaymentStatus(PaymentStatus.PAYMENT_COMPLETED);
            try {
                inventory.setEventJson(objectMapper.writeValueAsString(paymentEvent.getOrderResponseDto()));
            } catch (JsonProcessingException e) {
                logger.info("cast object exception");
                throw new RuntimeException(e);
            }
        }
        inventoryOutBoxRepository.save(inventory);
    }

}
