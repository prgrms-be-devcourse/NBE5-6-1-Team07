package com.grepp.team07.app.model.delivery;

import com.grepp.team07.app.model.delivery.dto.DeliveryDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DeliveryRepository {

    List<DeliveryDto> selectAll();
}
