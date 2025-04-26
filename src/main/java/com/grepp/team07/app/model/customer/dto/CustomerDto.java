package com.grepp.team07.app.model.customer.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CustomerDto {
    private String userId;
    private String email;
    private String password;
    private String address;
    private String postCode;
}
