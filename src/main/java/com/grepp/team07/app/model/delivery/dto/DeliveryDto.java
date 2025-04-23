package com.grepp.team07.app.model.delivery.dto;

import com.grepp.team07.app.model.delivery.code.DeliveryStatus;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class DeliveryDto {
    private Integer deliveryId;
    private Integer orderId;
    private DeliveryStatus deliveryStatus;
    private LocalDateTime deliveredAt;
}
