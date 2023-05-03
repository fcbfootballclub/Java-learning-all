package org.example.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import javax.persistence.*;
import java.util.Date;

@Table(name = "order_event_out_box")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class OrderEventOutBox {
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
