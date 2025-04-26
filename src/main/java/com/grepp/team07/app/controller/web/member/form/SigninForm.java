package com.grepp.team07.app.controller.web.member.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class SigninForm {

    @NotBlank
    private String userId;

    @NotBlank
    @Size(min = 4, max = 10)
    private String password;

}
