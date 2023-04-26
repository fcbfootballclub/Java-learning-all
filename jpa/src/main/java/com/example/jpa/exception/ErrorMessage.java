package com.example.jpa.exception;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Builder
@Getter
@Setter
public class ErrorMessage {
    private String message;
    private String url;
    private List<Error> list;
    @Builder
    @Getter
    @Setter
    public static class Error {
        String code;
        String message;
    }
}
