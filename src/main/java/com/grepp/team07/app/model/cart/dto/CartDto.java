package com.grepp.team07.app.model.cart.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CartDto {

    private int cartId;
    private int customerId; // customer 테이블의 id 참조
    private boolean isActivated;
}
