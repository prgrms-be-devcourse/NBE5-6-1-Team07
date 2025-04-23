package com.grepp.team07.app.model.ordered.dto;

import com.grepp.team07.app.model.delivery.code.DeliveryState;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
public class OrderedDto {
    private Integer orderId;
    private Integer cartId;
    private String email;
    private String name;
    private DeliveryState status;
    private String address;
    private List<OrderedProductDto> orderedProducts;
    private LocalDateTime orderedAt;
}
