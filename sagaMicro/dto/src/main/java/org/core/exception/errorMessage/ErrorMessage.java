package org.core.exception.errorMessage;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ErrorMessage {
    private int code;
    private String message;
}
