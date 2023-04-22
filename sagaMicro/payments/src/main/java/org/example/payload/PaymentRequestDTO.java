package org.example.payload;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.utils.PaymentMode;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class PaymentRequestDTO {
    private long orderId;
    private long amount;
    private PaymentMode paymentMode;
}
