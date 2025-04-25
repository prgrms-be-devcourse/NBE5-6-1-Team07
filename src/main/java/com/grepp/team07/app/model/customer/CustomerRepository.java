package com.grepp.team07.app.model.customer;

import com.grepp.team07.app.model.customer.dto.CustomerDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface CustomerRepository {
    CustomerDto findById(@Param("userId") String userId);

    boolean existsCustomer(String userId);

    void update(@Param("userId")String userId, @Param("dto")CustomerDto dto);
}
