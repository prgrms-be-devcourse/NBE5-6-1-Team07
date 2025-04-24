package com.grepp.team07.app.model.member.dto;

import com.grepp.team07.app.model.auth.code.Role;
import java.time.LocalDateTime;
import java.util.List;
import org.apache.ibatis.type.Alias;

@Alias("MEMBER_PRINCIPAL")
public record Principal(
    String userId,
    List<Role> Roles
) {

}
