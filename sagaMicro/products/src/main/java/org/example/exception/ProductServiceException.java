package org.example.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductServiceException extends RuntimeException {
    private int errorCode;

    public ProductServiceException(String message, int errorCode) {
        super(message);
        this.errorCode = errorCode;
    }
}
