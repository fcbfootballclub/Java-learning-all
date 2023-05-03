package org.example.event;

import lombok.*;
import org.example.demo.PaymentStatus;
import org.example.dto.OrderResponseDto;
import org.example.dto.PaymentRequestDto;
import org.example.entity.PaymentEventOutBox;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@NoArgsConstructor
@Builder
@ToString
@AllArgsConstructor
@Data
public class PaymentEvent implements  Event, Serializable  {
    private static final long serialVersionUID = 85824334376017889L;

    private PaymentRequestDto paymentRequestDto;
    private PaymentStatus paymentStatus;
    private OrderResponseDto orderResponseDto;

}
