package org.example.exceptions.remoteApiException;

import lombok.extern.slf4j.Slf4j;
import org.example.exceptions.OrderServiceCustomException;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.IOException;

import static org.springframework.http.HttpStatus.Series.CLIENT_ERROR;
import static org.springframework.http.HttpStatus.Series.SERVER_ERROR;

@Component
@Slf4j
public class RestTemplateResponseErrorHandler implements ResponseErrorHandler {
    @Override
    public boolean hasError(ClientHttpResponse response) throws IOException {
        return response.getStatusCode().series() == CLIENT_ERROR
                || response.getStatusCode().series() == SERVER_ERROR;
    }

    @Override
    public void handleError(ClientHttpResponse response) throws IOException {
        log.info(response.toString());
        log.info("get status code: ");
        log.info(response.getStatusCode().toString());
        log.info("end of code:");
        if (response.getStatusCode()
                .series() == HttpStatus.Series.SERVER_ERROR) {
            // handle SERVER_ERROR
            log.info("server error");
        } else if (response.getStatusCode()
                .series() == HttpStatus.Series.CLIENT_ERROR) {
            // handle CLIENT_ERROR
                log.info("client error");
            if (response.getRawStatusCode() == HttpStatus.NOT_FOUND.value()) {
                log.info("product not found");
                throw new OrderServiceCustomException("product not found!", "Not_found", 404);
            } else if (response.getRawStatusCode() == HttpStatus.BAD_REQUEST.value()){
                throw new OrderServiceCustomException("product exist!", "bad_request", 400);
            } else {
                throw new OrderServiceCustomException("ahihi do ngok!", "bad_request", 401);
            }
        }
    }
}
