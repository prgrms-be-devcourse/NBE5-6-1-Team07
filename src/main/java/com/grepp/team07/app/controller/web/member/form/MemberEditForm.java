package com.grepp.team07.app.controller.web.member.form;

import com.grepp.team07.app.model.member.dto.Member;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class MemberEditForm {
    @NotBlank
    private String userId;
    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String checkpassword;

    private String password;
    @NotBlank
    private String address;
    @NotBlank
    private String postCode;

    public Member toDto() {
        Member customer = new Member();
        customer.setUserId(userId);
        customer.setPassword(password);
        customer.setEmail(email);
        customer.setAddress(address);
        customer.setPostCode(postCode);
        return customer;
    }

}
