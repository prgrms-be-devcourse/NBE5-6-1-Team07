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

    List<OrderedDto> searchByEmail(@Param("limit") int limit, @Param("offset") int offset, String email);
    int countEmailOrders(String email);

    List<OrderedDto> searchByMemberEmail(@Param("limit") int limit, @Param("offset") int offset, String email);
    int countMemberEmailOrders(String email);
}
