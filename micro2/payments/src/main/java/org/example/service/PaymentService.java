package org.example.service;

import org.example.payload.PaymentRequestDTO;
import org.example.payload.PaymentResponseDTO;

public interface PaymentService {
    long doPayment(PaymentRequestDTO paymentRequest);
    PaymentResponseDTO getPaymentDetailsByOrderId(long orderId);
}
