package com.grepp.team07.app.model.delivery;

import com.grepp.team07.app.model.delivery.dto.DeliveryDto;
import com.grepp.team07.app.model.ordered.dto.OrderedDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class DeliveryService {

    private final DeliveryRepository deliveryRepository;

    public List<DeliveryDto> findAll() {
        return deliveryRepository.selectAll();
    }
}
