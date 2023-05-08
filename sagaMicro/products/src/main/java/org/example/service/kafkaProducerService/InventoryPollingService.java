package org.example.service.kafkaProducerService;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.core.enumPac.InventoryStatus;
import org.core.event.InventoryEvent;
import org.example.entity.OutBox.InventoryOutBox;
import org.core.event.PaymentEvent;
import org.example.repository.InventoryOutBoxRepository;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@EnableScheduling
@Component
public class InventoryPollingService {
    @Autowired
    private InventoryOutBoxRepository inventory;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private Logger logger;
    @Autowired
    private KafkaTemplate<String, InventoryEvent> kafkaTemplate;
    @Scheduled(fixedDelay = 1000)
    public void inventoryProducer() {
        List<InventoryOutBox> taskList = inventory.findAllByPublicStatus(false);
        for(InventoryOutBox event : taskList) {
            PaymentEvent paymentEvent;
            try {
                paymentEvent = objectMapper.readValue(event.getEventJson(), PaymentEvent.class);
            } catch (JsonProcessingException e) {
                logger.error("cast object info :" + e);
                continue;
            }
            InventoryEvent inventoryEvent = InventoryEvent.builder()
                    .inventoryStatus(InventoryStatus.RESERVED_SUCCESSFULLY)
                    .paymentEvent(paymentEvent)
                    .build();
            kafkaTemplate.send(event.getTopicName().toLowerCase(), inventoryEvent);
        }
    }
}
