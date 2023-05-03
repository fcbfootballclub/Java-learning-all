package org.example.service.kafkaProducerService;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.entity.PaymentEventOutBox;
import org.example.event.PaymentEvent;
import org.example.repository.PaymentOutBoxRepository;
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
public class PaymentPollingService {
    @Value("${spring.kafka.topic.name-inventory-request}")
    String topicName;

    @Autowired
    private PaymentOutBoxRepository paymentOutBoxRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private KafkaTemplate<String, PaymentEvent> kafkaTemplate;

    @Autowired
    private Logger logger;
    @Scheduled(fixedDelay = 5000)
    public void schedulerInventoryRequest() {
        List<PaymentEventOutBox> listInventoryRequest = paymentOutBoxRepository.findAllByPublicStatus(false);
        if(listInventoryRequest.size() == 0) return;
        for(PaymentEventOutBox event : listInventoryRequest) {
            PaymentEvent paymentEvent;
            try {
                paymentEvent = objectMapper.readValue(event.getEventJson(), PaymentEvent.class);
            } catch (JsonProcessingException e) {
                logger.error("Error casting to payment event " + e);
                throw new RuntimeException(e);
            }
            kafkaTemplate.send(topicName, event.getId().toString(), paymentEvent)
                    .addCallback(new KafkaSendCallback<>() {
                        @Override
                        public void onSuccess(SendResult<String, PaymentEvent> result) {
                            event.setPublicStatus(true);
                            paymentOutBoxRepository.save(event);
                        }
                        @Override
                        public void onFailure(KafkaProducerException e) {
                            logger.error("error sending payment event " + e);
                        }
                    });
        }
    }
}
