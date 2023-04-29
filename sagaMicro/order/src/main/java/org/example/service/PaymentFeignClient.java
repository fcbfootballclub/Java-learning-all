package org.example.service;


import org.example.config.Config;
import org.example.config.FeignConfig;
import org.example.dto.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@FeignClient(name = "payment-client" ,
        url = "http://localhost:8082/payment",
        configuration = FeignConfig.class)
public interface PaymentFeignClient {
    @PostMapping(path = "{id}")
    User getUserById(@PathVariable(value = "id") Integer id);
}
