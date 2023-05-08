package com.viettelidc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class GatewayApi {
    public static void main(String[] args) {
        SpringApplication.run(GatewayApi.class, args);
    }
}
