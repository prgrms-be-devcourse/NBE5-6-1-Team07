package com.grepp.team07.app.model.customer;

import com.grepp.team07.app.model.customer.dto.CustomerDto;
import com.grepp.team07.infra.exceptions.CommonException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerDto findById(String userId) {
        return customerRepository.findById(userId);
    }

    @Transactional
    public void edit(CustomerDto dto) {
        customerRepository.update(dto.getUserId(), dto);

    }
}
