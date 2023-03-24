package org.example.exception;

import org.example.payload.ErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class StudentServiceExceptionHandler {

    @ExceptionHandler(StudentServiceException.class)
    public ResponseEntity<?> handleError(StudentServiceException ex) {
        return ResponseEntity.status(ex.getHttpCode()).body(ErrorResponse.builder()
                .message(ex.getMessage())
                .errorCode(ex.getHttpCode())
                .build());
    }
}
