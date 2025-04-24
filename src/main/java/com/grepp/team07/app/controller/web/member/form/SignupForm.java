package com.grepp.team07.app.controller.web.member.form;

import com.grepp.team07.app.model.member.dto.Member;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class SignupForm {
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

    public Member toDto(){
        Member member = new Member();
        member.setUserId(userId);
        member.setEmail(email);
        member.setPassword(password);
        member.setAddress(address);
        member.setPostCode(postCode);
        return member;
    }
}