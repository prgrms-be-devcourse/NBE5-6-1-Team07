package com.grepp.team07.app.model.product.dto;

import java.time.LocalDateTime;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductDto {

    private Integer productId;
    private String name;
    private String price;
    private String info;
    private String brand;
    private List<ProductImgDto> images;
    private Integer count = 10;
    private Integer sales = 0;
    private LocalDateTime deletedAt;


    public ProductDto(String name, String price, String info, String brand) {
        this.name = name;
        this.price = price;
        this.info = info;
        this.brand = brand;
    }
}
