package com.example.jpa.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

@org.springframework.web.bind.annotation.ControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler({MethodArgumentNotValidException.class})
    ResponseEntity<?> handle(MethodArgumentNotValidException ex, ServletWebRequest webRequest) {
        System.out.println("fdsa");
        List<ObjectError> allErrors = ex.getBindingResult().getAllErrors();
        List<ErrorMessage.Error> errorList = new ArrayList<>();
        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
        for(FieldError fieldError : fieldErrors) {
            errorList.add(
                    ErrorMessage.Error.builder()
                            .code(fieldError.getField())
                            .message(fieldError.getDefaultMessage())
                            .build()
            );
        }
        return ResponseEntity.ok().body(
                ErrorMessage.builder()
                        .message(ex.getBindingResult().getAllErrors().get(0).getDefaultMessage())
                        .list(errorList)
                        .url(webRequest.getRequest().getRequestURI().toString()
                        )
                        .build()
        );
    }

//    @ExceptionHandler(BindException.class)
//    ResponseEntity<?> handle2(BindException ex) {
//        System.out.println("re");
//        return ResponseEntity.ok().body(
//                ErrorMessage.builder()
//                        .code(ex.getTarget().toString())
//                        .message(ex.getFieldErrors().get(0).toString())
//                        .url(ex.getNestedPath())
//        );
//    }
}
