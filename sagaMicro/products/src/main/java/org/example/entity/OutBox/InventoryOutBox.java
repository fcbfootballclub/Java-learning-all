package org.example.entity.OutBox;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import javax.persistence.*;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name = "inventory_out_box")
public class InventoryOutBox {
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
