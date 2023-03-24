package org.example.entity;

import lombok.*;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "transaction_details")
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
@Builder
public class TransactionDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_id")
    private Long id;

    @Column(name = "order_id")
    private Long orderId;

    @Column(name = "mode")
    private String paymentMode;

    @Column(name = "payment_date")
    private Instant paymentDate;

    @Column(name = "status")
    private String paymentStatus;

    @Column(name = "amount")
    private long amount;

}
