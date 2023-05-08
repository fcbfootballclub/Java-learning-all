package org.order.service;


import org.order.config.FeignConfig;
import org.core.dto.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@FeignClient(name = "payment-client" ,
        url = "http://localhost:8082/payment",
        configuration = FeignConfig.class)
public interface PaymentFeignClient {
    @PostMapping(path = "{id}")
    UserDto getUserById(@PathVariable(value = "id") Integer id);
}
