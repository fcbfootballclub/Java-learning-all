package org.core.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InventoryOutBoxDto {
    private Integer id;
    private String topicName;
    private String eventJson;
    private boolean publicStatus;
    private Date createdDate;
}
