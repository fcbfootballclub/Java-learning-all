package org.order.controler;

import org.core.dto.OrderRequestDto;
import org.order.entity.PurchaseOrder;
import org.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/order")
public class OrderControler {
    @Autowired
    private OrderService orderService;
    @PostMapping(path = "/create")
    public ResponseEntity<?> createOrder(@RequestBody OrderRequestDto orderRequestDto) {
        return ResponseEntity.ok(orderService. createOrder(orderRequestDto));
    }

    @GetMapping
    public List<PurchaseOrder> getOrders() {
        return orderService.getAllOrders();
    }


}
