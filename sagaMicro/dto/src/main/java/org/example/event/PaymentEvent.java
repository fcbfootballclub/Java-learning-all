package org.example.event;

import lombok.*;
import org.example.dto.PaymentRequestDto;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@NoArgsConstructor
@Getter
@Setter
@Builder
@AllArgsConstructor
@ToString
public class PaymentEvent implements  Event, Serializable  {
    private static final long serialVersionUID = 85824334376017889L;
    private UUID eventId = UUID.randomUUID();
    private Date date = new Date();

    private PaymentRequestDto paymentRequestDto;
    private PaymentStatus paymentStatus;
    @Override
    public UUID getEventId() {
        return eventId;
    }

    @Override
    public Date getDate() {
        return date;
    }

    public PaymentEvent(PaymentRequestDto paymentRequestDto, PaymentStatus paymentStatus) {
        this.paymentRequestDto = paymentRequestDto;
        this.paymentStatus = paymentStatus;
    }
}
