package org.example.service.kafkaListenerService;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.demo.CONSTANT;
import org.example.entity.OutBox.InventoryOutBox;
import org.example.event.PaymentEvent;
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
            groupId ="${spring.kafka.consumer.group-id}"
    )
    @Transactional
    public void deductQuantity(PaymentEvent paymentEvent) {
        logger.info(String.format("Json message recieved in product -> %s", paymentEvent.toString()));
        InventoryOutBox inventory;
        try {
            inventory = InventoryOutBox.builder()
                    .id(paymentEvent.getPaymentRequestDto().getOrderId())
                    .eventJson(objectMapper.writeValueAsString(paymentEvent.getOrderResponseDto()))
                    .publicStatus(false)
                    .build();
        } catch (JsonProcessingException e) {
            logger.error("cast object exception " + e);
            throw new RuntimeException(e);
        }
        boolean check = productService.reduceQuantity(paymentEvent.getOrderResponseDto().getProductId(), paymentEvent.getOrderResponseDto().getQuantity());
        if(check) {
            inventory.setTopicName(CONSTANT.TOPIC.ORDER_COMPLETED_REQUEST.toString());
        } else {
            inventory.setTopicName(CONSTANT.TOPIC.INVENTORY_FAILED.toString());
        }
        inventoryOutBoxRepository.save(inventory);
    }

}
