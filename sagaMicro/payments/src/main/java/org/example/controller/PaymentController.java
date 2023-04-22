package org.example.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.payload.PaymentRequestDTO;
import org.example.payload.PaymentResponseDTO;
import org.example.service.PaymentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "payment")
@CrossOrigin
@RequiredArgsConstructor
@Slf4j
public class PaymentController {
    private final PaymentService paymentService;

    @PostMapping(path = "")
    public ResponseEntity<?> doPayment(@RequestBody PaymentRequestDTO paymentRequest) {
        log.info("PaymentController | doPayment is called");
        log.info("PaymentController | doPayment | paymentRequest : " + paymentRequest.toString());
        return new ResponseEntity<>(paymentService.doPayment(paymentRequest), HttpStatus.OK);
    }

    @GetMapping("/order/{orderId}")
    public ResponseEntity<PaymentResponseDTO> getPaymentDetailsByOrderId(@PathVariable long orderId) {
        log.info("PaymentController | doPayment is called");
        log.info("PaymentController | doPayment | orderId : " + orderId);
        return new ResponseEntity<>(
                paymentService.getPaymentDetailsByOrderId(orderId),
                HttpStatus.OK
        );
    }
}
