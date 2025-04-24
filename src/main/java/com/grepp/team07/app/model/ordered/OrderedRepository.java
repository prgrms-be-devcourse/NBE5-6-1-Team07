package com.grepp.team07.app.model.ordered;

import com.grepp.team07.app.model.ordered.dto.OrderedDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface OrderedRepository {

    List<OrderedDto> selectAll();

    int countAllOrders();

    List<OrderedDto> findPaged(@Param("limit") int limit, @Param("offset") int offset);
}
