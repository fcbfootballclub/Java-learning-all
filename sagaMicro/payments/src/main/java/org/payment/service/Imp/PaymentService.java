package org.payment.service.Imp;

import org.core.dto.OrderResponseDto;
import org.core.dto.PaymentRequestDto;
import org.payment.entity.UserBalance;
import org.payment.entity.UserTransaction;
import org.core.event.PaymentEvent;
import org.core.enumPac.PaymentStatus;
import org.payment.repository.UserRepository;
import org.payment.repository.UserTransactionRepository;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class PaymentService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserTransactionRepository userTransactionRepository;
    @Autowired
    private Logger logger;

    public boolean checkBalance(OrderResponseDto order) {
        Optional<UserBalance> user = userRepository.findById(order.getUserId());
        if (user.isPresent()) {
            return user.get().getPrice() >= order.getAmount();
        }
        logger.error("not enough money");
        throw new RuntimeException("not enough money");
    }

    public PaymentEvent processPayment(OrderResponseDto order) {
        PaymentRequestDto paymentRequestDto = PaymentRequestDto.builder()
                .userId(order.getUserId())
                .orderId(order.getOrderId())
                .amount(order.getAmount())
                .build();
        if (checkBalance(order)) {
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
                                .orderResponseDto(order)
                                .paymentStatus(PaymentStatus.PAYMENT_COMPLETED)
                                .build();
                    })
                    .orElseThrow(
                            () -> new RuntimeException("user not found")
                    );
        }
        return PaymentEvent.builder()
                .paymentRequestDto(paymentRequestDto)
                .paymentStatus(PaymentStatus.PAYMENT_FAILED)
                .orderResponseDto(order)
                .build();
    }
}
