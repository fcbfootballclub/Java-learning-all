package org.order.controler;

import org.order.exception.FeignClientException;
import org.core.exception.errorMessage.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class OrderControllerAdvice {

    @ExceptionHandler(FeignClientException.class)
    public ResponseEntity<?> handleRestApiException(FeignClientException ex) {
        return ResponseEntity.ok(
                ErrorMessage.builder()
                        .code(HttpStatus.NOT_FOUND.value())
                        .message(ex.getMessage())
                        .build()
        );
    }
}
