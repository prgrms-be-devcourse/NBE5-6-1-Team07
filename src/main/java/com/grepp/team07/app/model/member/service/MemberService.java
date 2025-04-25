package com.grepp.team07.app.model.member.service;

import com.grepp.team07.app.model.auth.code.Role;
import com.grepp.team07.app.model.member.dto.Member;
import com.grepp.team07.app.model.member.repository.MemberRepository;
import com.grepp.team07.infra.error.exceptions.CommonException;
import com.grepp.team07.infra.response.ResponseCode;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void signup(Member dto, Role role) {
        if(memberRepository.existsMember(dto.getUserId()))
            throw new CommonException(ResponseCode.BAD_REQUEST);

        String encodedPassword = passwordEncoder.encode(dto.getPassword());
        dto.setPassword(encodedPassword);

        dto.setRole(role);
        memberRepository.insert(dto);
    }

    public Boolean isDuplicatedId(String id) {
        return memberRepository.existsMember(id);
    }

}
