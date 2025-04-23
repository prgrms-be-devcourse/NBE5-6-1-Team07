package com.grepp.team07.app.model.ordered.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class OrderedDto {
    private Integer orderId;
    private Integer cartId;
    private String email;
    private Boolean isCustomer;
    private String address;
    private Integer postCode;
    private LocalDateTime orderedAt;
}
