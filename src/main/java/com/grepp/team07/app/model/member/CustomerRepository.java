package com.grepp.team07.app.model.member;

import com.grepp.team07.app.model.member.dto.CustomerDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface CustomerRepository {
    CustomerDto findById(@Param("userId") String userId);

    boolean existsCustomer(String userId);

    void update(CustomerDto dto);
}
