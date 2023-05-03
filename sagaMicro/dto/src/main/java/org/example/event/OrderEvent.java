package org.example.event;

import lombok.*;
import org.example.demo.OrderStatus;
import org.example.dto.OrderRequestDto;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@NoArgsConstructor
@Getter
@Setter
@Builder
public class OrderEvent implements Event, Serializable
{
    private static final long serialVersionUID = 8582433437601788991L;
    private OrderRequestDto orderRequestDto;
    private OrderStatus orderStatus;

    public OrderEvent(OrderRequestDto orderRequestDto, OrderStatus orderStatus) {
        this.orderRequestDto = orderRequestDto;
        this.orderStatus = orderStatus;
    }
}
