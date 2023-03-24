package org.example.exceptions;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.BufferedReader;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderServiceCustomException extends RuntimeException {
    private String errorCode;
    private int status;
    public OrderServiceCustomException(String message, String errorCode, int status) {
        super(message);
        this.errorCode = errorCode;
        this.status = status;
    }
}
