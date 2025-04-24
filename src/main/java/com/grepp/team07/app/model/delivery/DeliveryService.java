package com.grepp.team07.app.model.delivery;

import com.grepp.team07.app.model.delivery.code.DeliveryState;
import com.grepp.team07.app.model.delivery.dto.DeliveryDto;
import com.grepp.team07.app.model.ordered.dto.OrderedDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class DeliveryService {

    private final DeliveryRepository deliveryRepository;

    public List<DeliveryDto> findAll() {
        return deliveryRepository.selectAll();
    }

    @Transactional
    public void sendProduct(Integer orderId) {
        DeliveryDto delivery = deliveryRepository.findByOrderId(orderId);
        if(delivery.getStatus() != DeliveryState.READY){
            return;
        }

        delivery.setDeliveredAt(LocalDateTime.now());
        delivery.setStatus(DeliveryState.SHIPPED);

        deliveryRepository.updateDelivery(delivery.getDeliveryId(), delivery.getDeliveredAt(), delivery.getStatus());
    }
}
