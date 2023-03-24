package org.example.exceptions;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Data
@EqualsAndHashCode(callSuper = false)
public class PaymentServiceCustomException extends RuntimeException {
    private final String errorCode;

    public PaymentServiceCustomException(String errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }
}
