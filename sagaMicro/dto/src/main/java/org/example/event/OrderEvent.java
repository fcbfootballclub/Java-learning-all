package org.example.event;

import lombok.*;
import org.example.dto.OrderRequestDto;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@NoArgsConstructor
@Getter
@Setter
@Builder
@AllArgsConstructor
public class OrderEvent implements Event, Serializable
{
    private static final long serialVersionUID = 8582433437601788991L;
    private UUID eventId = UUID.randomUUID();
    private Date date = new Date();
    private OrderRequestDto orderRequestDto;
    private OrderStatus orderStatus;

    @Override
    public UUID getEventId() {
        return eventId;
    }

    @Override
    public Date getDate() {
        return date;
    }

    public OrderEvent(OrderRequestDto orderRequestDto, OrderStatus orderStatus) {
        this.orderRequestDto = orderRequestDto;
        this.orderStatus = orderStatus;
    }
}
