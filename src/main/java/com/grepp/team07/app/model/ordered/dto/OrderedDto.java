package com.grepp.team07.app.model.ordered.dto;

import com.grepp.team07.app.model.delivery.code.DeliveryState;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Data
@NoArgsConstructor
public class OrderedDto {
    private Integer orderId;
    private Integer cartId;
    private String email;
    private String name;
    private DeliveryState status;
    private LocalDateTime deliveredAt;
    private String address;
    private List<OrderedProductDto> orderedProducts;
    private LocalDateTime orderedAt;
    private Integer totalPrice;

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public String getOrderedDate() {
        return orderedAt != null ? orderedAt.format(DATE_FORMATTER) : null;
    }

    public String getDeliveredDate() {
        return deliveredAt != null ? deliveredAt.plusDays(1).format(DATE_FORMATTER) : null;
    }
}
