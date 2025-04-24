package com.grepp.team07.app.model.member.repository;

import com.grepp.team07.app.model.member.dto.Member;
import java.util.Optional;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface MemberRepository {

    Optional<Member> selectByUserId(String userId);

    @Select("select count(*) from customer where user_id = #{userId}")
    boolean existsMember(String userId);

    @Insert("insert into customer (user_id, password, email, address, post_code) "
        + "values(#{userId}, #{password}, #{email}, #{address}, #{postCode})")
    void insert(Member dto);


}