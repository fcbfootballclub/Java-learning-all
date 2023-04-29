package org.example.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "user_transaction")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer orderId;

    @Column(
            name = "user_id"
    )
    private Integer userId;
    @Column(name = "amount")
    private Integer amount;
}
