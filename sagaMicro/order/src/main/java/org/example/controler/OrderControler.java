package org.example.controler;

import org.example.dto.OrderRequestDto;
import org.example.entity.PurchaseOrder;
import org.example.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/order")
public class OrderControler {
    @Autowired
    private OrderService orderService;
    @PostMapping(path = "/create")
    public PurchaseOrder createOrder(@RequestBody OrderRequestDto orderRequestDto) {
        return orderService.createOrder(orderRequestDto);
    }

    @GetMapping
    public List<PurchaseOrder> getOrders() {
        return orderService.getAllOrders();
    }


}
