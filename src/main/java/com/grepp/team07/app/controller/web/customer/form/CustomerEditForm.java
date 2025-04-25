package com.grepp.team07.app.controller.web.customer.form;

import com.grepp.team07.app.model.customer.dto.CustomerDto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CustomerEditForm {
    @NotBlank
    private String userId;
    @NotBlank
    private String email;
    @NotBlank
    private String password;
    @NotBlank
    private String address;
    @NotNull
    private Integer postCode;

    public CustomerDto toDto() {
        CustomerDto customer = new CustomerDto();
        customer.setUserId(userId);
        customer.setPassword(password);
        customer.setEmail(email);
        customer.setAddress(address);
        customer.setPostCode(postCode);
        return customer;
    }
}
