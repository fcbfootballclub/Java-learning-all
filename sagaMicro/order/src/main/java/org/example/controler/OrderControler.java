package org.example.controler;

import com.netflix.discovery.converters.Auto;
import org.example.dto.OrderRequestDto;
import org.example.entity.PurchaseOrder;
import org.example.repository.OrderRepository;
import org.example.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/order")
public class OrderControler {
    @Autowired
    private OrderService orderService;
    @PostMapping(path = "/create")
    public PurchaseOrder createOrder(@RequestBody OrderRequestDto orderRequestDto) {
        return null;
    }
}
