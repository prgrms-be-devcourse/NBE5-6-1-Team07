package com.grepp.team07.app.model.ordered;

import com.grepp.team07.app.model.ordered.dto.OrderedDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations={
        "file:src/main/webapp/WEB-INF/spring/root-context.xml",
        "file:src/main/webapp/WEB-INF/spring/servlet-context.xml"
})
@Slf4j
class OrderedServiceTest {
    @Autowired
    private OrderedService orderedService;

    @Test
    public void selectAll(){
        List<OrderedDto> orders = orderedService.findAll();
        orders.forEach(System.out::println);
    }

}