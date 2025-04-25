package com.grepp.team07.app.model.member.dto;

import com.grepp.team07.app.model.auth.code.Role;
import lombok.Data;
import org.apache.ibatis.type.Alias;

@Data
@Alias("Member")
public class Member {
    private String userId;
    private String email;
    private Role role;
    private String password;
    private String address;
    private String postCode;
}
