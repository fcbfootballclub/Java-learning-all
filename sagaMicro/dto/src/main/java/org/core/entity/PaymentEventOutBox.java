package org.core.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "payment_event_out_box")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PaymentEventOutBox {
    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "topic_name")
    private String topicName;

    @Column(name = "event_json", length = 5000, nullable = false)
    private String eventJson;

    @Column(name = "public_status", nullable = false, columnDefinition = "TINYINT(1)")
    private boolean publicStatus;

    @Column(name = "created_date")
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
}
