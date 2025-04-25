package com.grepp.team07.app.model.member.repository;

import com.grepp.team07.app.model.member.dto.Member;
import java.util.Optional;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberRepository {
    Optional<Member> selectByUserId(String userId);
    boolean existsMember(String userId);
    void insert(Member member);
}