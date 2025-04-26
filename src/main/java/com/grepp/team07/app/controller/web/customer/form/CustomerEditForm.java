package com.grepp.team07.app.controller.web.customer.form;

import com.grepp.team07.app.model.customer.dto.CustomerDto;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CustomerEditForm {
    @NotBlank
    private String userId;
    @NotBlank
    @Email
    private String email;
    @NotBlank
    @Size(min = 4, max = 10)
    private String password;
    @NotBlank
    private String address;
    @NotBlank
    private String postCode;

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
