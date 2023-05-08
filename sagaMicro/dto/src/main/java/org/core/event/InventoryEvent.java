package org.core.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.core.enumPac.InventoryStatus;

import javax.persistence.Table;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table
@Data
public class InventoryEvent implements Event {
    PaymentEvent paymentEvent;
    InventoryStatus inventoryStatus;
}
