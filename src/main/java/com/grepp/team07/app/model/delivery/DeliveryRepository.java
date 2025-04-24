package com.grepp.team07.app.model.delivery;

import com.grepp.team07.app.model.delivery.code.DeliveryState;
import com.grepp.team07.app.model.delivery.dto.DeliveryDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface DeliveryRepository {

    List<DeliveryDto> selectAll();

    DeliveryDto findByOrderId(@Param("orderId") Integer orderId);

    void updateDelivery(@Param("deliveryId")Integer deliveryId, @Param("deliveredAt")LocalDateTime deliveredAt, @Param("status")DeliveryState status);
}
