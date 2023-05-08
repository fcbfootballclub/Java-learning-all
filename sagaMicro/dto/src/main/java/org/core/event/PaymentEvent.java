package org.core.event;

import lombok.*;
import org.core.dto.OrderResponseDto;
import org.core.dto.PaymentRequestDto;
import org.core.enumPac.PaymentStatus;

import java.io.Serializable;

@NoArgsConstructor
@Builder
@ToString
@AllArgsConstructor
@Data
public class PaymentEvent implements Event  {
    private PaymentRequestDto paymentRequestDto;
    private PaymentStatus paymentStatus;
    private OrderResponseDto orderResponseDto;
}
