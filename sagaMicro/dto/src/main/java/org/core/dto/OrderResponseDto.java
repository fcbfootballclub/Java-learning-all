package org.core.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.core.enumPac.OrderStatus;
import org.core.enumPac.PaymentStatus;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class OrderResponseDto {
    private Integer userId;
    private Integer productId;
    private Integer quantity;
    private Integer amount;
    private Integer orderId;
    private OrderStatus orderStatus;
    private PaymentStatus paymentStatus;
    private Date createdDate;
}
