package org.example.service;

import com.netflix.discovery.converters.Auto;
import org.example.dto.OrderRequestDto;
import org.example.entity.PurchaseOrder;
import org.example.event.OrderStatus;
import org.example.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderService {
    @Autowired private OrderRepository orderRepository;
    @Autowired private OrderStatusPublisher orderStatusPublisher;

    @Transactional
    public PurchaseOrder createOrder(OrderRequestDto orderRequestDto) {
        PurchaseOrder order = orderRepository.save(this.convertDtoToEntity(orderRequestDto));
        orderRequestDto.setOrderId(order.getId());

        //create kafka event
        orderStatusPublisher.publishOrderEvent(orderRequestDto, OrderStatus.ORDER_CREATED);
        return order;
    }

    public PurchaseOrder convertDtoToEntity(OrderRequestDto orderRequestDto) {
        PurchaseOrder purchaseOrder = PurchaseOrder.builder()
                .userId(orderRequestDto.getUserId())
                .productId(orderRequestDto.getProductId())
                .orderStatus(OrderStatus.ORDER_CREATED)
                .price(orderRequestDto.getAmount())
                .build();
        return purchaseOrder;
    }
}
