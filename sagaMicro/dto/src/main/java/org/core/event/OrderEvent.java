package org.core.event;

import lombok.*;
import org.core.dto.OrderRequestDto;
import org.core.enumPac.OrderStatus;

import java.io.Serializable;
;

@NoArgsConstructor
@Getter
@Setter
@Builder
public class OrderEvent implements Event {
    private OrderRequestDto orderRequestDto;
    private OrderStatus orderStatus;

    public OrderEvent(OrderRequestDto orderRequestDto, OrderStatus orderStatus) {
        this.orderRequestDto = orderRequestDto;
        this.orderStatus = orderStatus;
    }
}
