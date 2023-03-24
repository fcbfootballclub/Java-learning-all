package org.example.service;

import org.example.payload.OrderRequest;
import org.example.payload.OrderResponse;

public interface OrderService {
    long placeOrder(OrderRequest orderRequest);

    OrderResponse getOrderDetails(long orderId);
}
