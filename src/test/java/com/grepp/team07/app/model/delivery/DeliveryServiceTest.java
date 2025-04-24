package com.grepp.team07.app.model.delivery;

import com.grepp.team07.app.model.delivery.dto.DeliveryDto;
import com.grepp.team07.app.model.ordered.OrderedService;
import com.grepp.team07.app.model.ordered.dto.OrderedDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations={
        "file:src/main/webapp/WEB-INF/spring/root-context.xml",
        "file:src/main/webapp/WEB-INF/spring/servlet-context.xml"
})
class DeliveryServiceTest {
    @Autowired
    private DeliveryService deliveryService;

    @Test
    public void selectAll(){
        List<DeliveryDto> orders = deliveryService.findAll();
        orders.forEach(System.out::println);
    }

    @Test
    public void sendProduct(){
        deliveryService.sendProduct(3);
    }

}