package org.example.service;

import org.example.dto.OrderRequestDto;
import org.example.dto.User;
import org.example.entity.PurchaseOrder;
import org.example.event.OrderStatus;
import org.example.event.PaymentEvent;
import org.example.event.PaymentStatus;
import org.example.repository.OrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    @Autowired private OrderRepository orderRepository;
    @Autowired private OrderStatusPublisher orderStatusPublisher;
    @Autowired private PaymentFeignClient paymentFeignClient;
    private final Logger logger = LoggerFactory.getLogger(OrderService.class);

    @Transactional
    public PurchaseOrder createOrder(OrderRequestDto orderRequestDto) {
        User userById = paymentFeignClient.getUserById(orderRequestDto.getUserId());
        if(userById == null) {
            throw new RuntimeException("User not exists");
        }
        PurchaseOrder order = orderRepository.save(this.convertDtoToEntity(orderRequestDto));
        orderRequestDto.setOrderId(order.getId());

        //create kafka event
        orderStatusPublisher.publishOrderEvent(orderRequestDto, OrderStatus.ORDER_CREATED);
        return order;
    }

    public List<PurchaseOrder> getAllOrders() {
        return orderRepository.findAll();
    }

    public PurchaseOrder convertDtoToEntity(OrderRequestDto orderRequestDto) {
        return PurchaseOrder.builder()
                .userId(orderRequestDto.getUserId())
                .productId(orderRequestDto.getProductId())
                .orderStatus(OrderStatus.ORDER_CREATED)
                .price(orderRequestDto.getAmount())
                .paymentStatus(PaymentStatus.PENDING)
                .build();
    }

    @KafkaListener(
            topics = "${spring.kafka.topic.name-payment-response}",
            groupId ="${spring.kafka.consumer.group-id}"
    )
    public PurchaseOrder updateOrderStatus(PaymentEvent paymentEvent) {
        logger.info("payment event : " + paymentEvent);
        return orderRepository.findById(paymentEvent.getPaymentRequestDto().getOrderId())
                .map(order -> {
                    if(paymentEvent.getPaymentStatus().toString().equals("PAYMENT_COMPLETED")) {
                        order.setOrderStatus(OrderStatus.ORDER_COMPLETED);
                    } else {
                        order.setOrderStatus(OrderStatus.ORDER_CANCEL);
                    }
                    order.setPaymentStatus(paymentEvent.getPaymentStatus());
                    orderRepository.save(order);
                    return order;
                })
                .orElseThrow();
    }
}
