package org.order.config;

import feign.Retryer;
import feign.codec.ErrorDecoder;
import org.order.exception.CustomErrorDecoded;
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
