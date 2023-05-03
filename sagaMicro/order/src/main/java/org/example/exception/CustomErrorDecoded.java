package org.example.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Response;
import feign.codec.ErrorDecoder;
import org.apache.commons.io.IOUtils;
import org.example.exception.errorMessage.ErrorMessage;

import java.io.IOException;

public class CustomErrorDecoded implements ErrorDecoder {
    private final ErrorDecoder errorDecoder = new Default();
    @Override
    public Exception decode(String methodKey, Response response) {
        ErrorMessage errorMessage;
        try {
            String details = IOUtils.toString(response.body().asInputStream(), "UTF-8");
            ObjectMapper mapper = new ObjectMapper();
            errorMessage = mapper.readValue(details, ErrorMessage.class);
        } catch (IOException e) {
            e.printStackTrace();
            // you could also return an exception
            return new RuntimeException(e.getMessage());
        }
        switch (response.status()) {
            case 400:
                return new RuntimeException(errorMessage.getMessage() != null ? errorMessage.getMessage() : "Bad Request");
            case 404:
                return new FeignClientException(errorMessage.getMessage());
            default:
                return errorDecoder.decode(methodKey, response);
        }

    }
}
