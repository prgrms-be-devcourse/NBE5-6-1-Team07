package com.grepp.team07.app.model.product.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Product {

    private int productId;
    private String name;
    private String image;
    private Integer price;
    private String info;
    private String brand;
    private Integer count;
    private Integer sales;
}
