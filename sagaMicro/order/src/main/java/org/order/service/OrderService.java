package org.order.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.core.enumPac.CONSTANT;
import org.core.dto.OrderRequestDto;
import org.core.dto.OrderResponseDto;
import org.core.dto.UserDto;
import org.core.entity.OrderEventOutBox;
import org.order.entity.PurchaseOrder;
import org.core.enumPac.OrderStatus;
import org.core.enumPac.PaymentStatus;
import org.order.repository.OrderEventOutBoxRepository;
import org.order.repository.OrderRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrderService {
    @Autowired private OrderRepository orderRepository;
    @Autowired private OrderEventOutBoxRepository orderEventOutBoxRepository;
    @Autowired private PaymentFeignClient paymentFeignClient;
    @Autowired private ModelMapper modelMapper;
    @Autowired
    private Logger logger;

    @Transactional
    public OrderResponseDto createOrder(OrderRequestDto orderRequestDto) {
        UserDto userById = paymentFeignClient.getUserById(orderRequestDto.getUserId());
        if(userById == null) {
            throw new RuntimeException("User not exists");
        }
        PurchaseOrder order = orderRepository.save(this.convertDtoToEntity(orderRequestDto));
        OrderResponseDto orderResponseDto = modelMapper.map(order, OrderResponseDto.class);

        //save event to out box table
        ObjectMapper objectMapper = new ObjectMapper();
        OrderEventOutBox event = null;
        try {
            event = OrderEventOutBox.builder()
                    .id(orderResponseDto.getOrderId())
                    .topicName(CONSTANT.TOPIC.PAYMENT_REQUEST.toString())
                    .eventJson(objectMapper.writeValueAsString(orderResponseDto))
                    .publicStatus(false)
                    .build();

        } catch (JsonProcessingException e) {
            logger.error("Error saving order");
            throw new RuntimeException(e);
        }
        orderEventOutBoxRepository.save(event);
        return orderResponseDto;
    }

    public List<PurchaseOrder> getAllOrders() {
        return orderRepository.findAll();
    }

    public PurchaseOrder convertDtoToEntity(OrderRequestDto orderRequestDto) {
        return PurchaseOrder.builder()
                .userId(orderRequestDto.getUserId())
                .productId(orderRequestDto.getProductId())
                .quantity(orderRequestDto.getQuantity())
                .orderStatus(OrderStatus.ORDER_CREATED)
                .amount(orderRequestDto.getAmount())
                .paymentStatus(PaymentStatus.PENDING)
                .build();
    }

/*    @KafkaListener(
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
    }*/
}
