package org.example.config;

import feign.Retryer;
import feign.codec.ErrorDecoder;
import org.example.exception.CustomErrorDecoded;
import org.springframework.context.annotation.Bean;

public class FeignConfig {
    @Bean
    public ErrorDecoder errorDecoder() {
        return new CustomErrorDecoded();
    }
    @Bean
    public Retryer retryer(){
        return new Retryer.Default();
    }

}
