package com.grepp.team07.app.model.ordered.dto;

import lombok.Data;

@Data
public class OrderedProductDto {
    private Integer orderProductId;
    private Integer orderId;
    private String name;
    private Integer count;
}
