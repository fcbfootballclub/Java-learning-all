package org.order.exception;

public class FeignClientException extends RuntimeException {
    public FeignClientException() {
    }

    public FeignClientException(String message) {
        super(message);
    }

    public FeignClientException(String message, Throwable cause) {
        super(message, cause);
    }

    public FeignClientException(Throwable cause) {
        super(cause);
    }

    public FeignClientException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
