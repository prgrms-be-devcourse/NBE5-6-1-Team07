package com.grepp.team07.app.model.cart.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CartProductDto {

    private int cartProductId;
    private int cartId;
    private int productId;
    private int count;
}
