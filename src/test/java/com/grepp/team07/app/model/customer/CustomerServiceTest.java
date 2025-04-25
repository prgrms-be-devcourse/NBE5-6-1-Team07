package com.grepp.team07.app.model.customer;

import com.grepp.team07.app.model.customer.dto.CustomerDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@Slf4j
@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations={
        "file:src/main/webapp/WEB-INF/spring/root-context.xml",
        "file:src/main/webapp/WEB-INF/spring/servlet-context.xml"
})
class CustomerServiceTest {

    @Autowired
    private CustomerService customerService;

    @Test
    public void findById(){
        CustomerDto dto = customerService.findById("user01");
    }

    @Test
    public void edit(){
        CustomerDto dto = new CustomerDto();
        dto.setUserId("user01");
        dto.setPassword("afsd");
        dto.setEmail("asf@asdf.com");
        dto.setPostCode(12332);
        dto.setAddress("afklsakskaf");
        customerService.edit(dto);
    }
}