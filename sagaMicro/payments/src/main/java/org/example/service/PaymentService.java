package org.example.service;

import org.example.dto.PaymentRequestDto;
import org.example.entity.UserBalance;
import org.example.entity.UserTransaction;
import org.example.event.OrderEvent;
import org.example.event.PaymentEvent;
import org.example.event.PaymentStatus;
import org.example.repository.UserRepository;
import org.example.repository.UserTransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class PaymentService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserTransactionRepository userTransactionRepository;

    public boolean checkBalance(OrderEvent orderEvent) {
        Optional<UserBalance> user = userRepository.findById(orderEvent.getOrderRequestDto().getUserId());
        if (user.isPresent()) {
            return user.get().getPrice() > orderEvent.getOrderRequestDto().getAmount();
        }
        throw new RuntimeException();
    }

    @Transactional
    public PaymentEvent processPayment(OrderEvent orderEvent) {
        PaymentRequestDto paymentRequestDto = PaymentRequestDto.builder()
                .userId(orderEvent.getOrderRequestDto().getUserId())
                .orderId(orderEvent.getOrderRequestDto().getOrderId())
                .amount(orderEvent.getOrderRequestDto().getAmount())
                .build();
        if (checkBalance(orderEvent)) {
            return userRepository.findById(paymentRequestDto.getUserId())
                    .map(ub ->
                    {
                        ub.setPrice(ub.getPrice() - paymentRequestDto.getAmount());
                        userRepository.save(ub);
                        UserTransaction transaction = UserTransaction.builder()
                                .userId(paymentRequestDto.getUserId())
                                .orderId(paymentRequestDto.getOrderId())
                                .amount(paymentRequestDto.getAmount())
                                .build();
                        userTransactionRepository.save(transaction);
                        return PaymentEvent.builder()
                                .paymentRequestDto(paymentRequestDto)
                                .paymentStatus(PaymentStatus.PAYMENT_COMPLETED)
                                .build();
                    })
                    .orElseThrow();
        }
        return PaymentEvent.builder()
                .paymentRequestDto(paymentRequestDto)
                .paymentStatus(PaymentStatus.PAYMENT_FAILED)
                .build();
    }
}
