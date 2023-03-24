package org.example.service;

import lombok.extern.slf4j.Slf4j;
import org.example.entity.TransactionDetails;
import org.example.exceptions.PaymentServiceCustomException;
import org.example.payload.PaymentRequestDTO;
import org.example.payload.PaymentResponseDTO;
import org.example.repository.TransactionDetailsRepository;
import org.example.utils.PaymentMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;

@Service
@Slf4j
public class PaymentServiceImpl implements PaymentService {
    private final TransactionDetailsRepository transactionDetailsRepository;

    @Autowired
    public PaymentServiceImpl(TransactionDetailsRepository transactionDetailsRepository) {
        this.transactionDetailsRepository = transactionDetailsRepository;
    }

    @Override
    public long doPayment(PaymentRequestDTO paymentRequest) {
        log.info("PaymentServiceImpl | doPayment is called");
        log.info("PaymentServiceImpl | doPayment | Recording Payment Details: {}", paymentRequest);

        TransactionDetails transactionDetails
                = TransactionDetails.builder()
                .paymentDate(Instant.now())
                .paymentMode(paymentRequest.getPaymentMode().name())
                .paymentStatus("SUCCESS")
                .orderId(paymentRequest.getOrderId())
                .amount(paymentRequest.getAmount())
                .build();
        transactionDetails = transactionDetailsRepository.save(transactionDetails);
        log.info("Transaction Completed with Id: {}", transactionDetails.getId());
        return transactionDetails.getId();
    }

    @Override
    public PaymentResponseDTO getPaymentDetailsByOrderId(long orderId) {
        Optional<TransactionDetails> findOrder = transactionDetailsRepository.findByOrderId(orderId);
        if (findOrder.isPresent()) {
            PaymentResponseDTO paymentResponseDTO = PaymentResponseDTO.builder()
                    .paymentId(findOrder.get().getId())
                    .paymentMode(PaymentMode.getMode(findOrder.get().getPaymentMode()))
                    .paymentDate(findOrder.get().getPaymentDate())
                    .amount(findOrder.get().getAmount())
                    .status(findOrder.get().getPaymentStatus())
                    .orderId(findOrder.get().getOrderId())
                    .build();
            return paymentResponseDTO;
        }
        throw new PaymentServiceCustomException("404", "Can not payment for this order id" + orderId);
    }
}
