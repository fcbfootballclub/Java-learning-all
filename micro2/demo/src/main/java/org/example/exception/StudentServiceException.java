package org.example.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentServiceException extends RuntimeException {
    private Integer httpCode;

    public StudentServiceException(String message, Integer httpCode) {
        super(message);
        this.httpCode = httpCode;
    }
}
