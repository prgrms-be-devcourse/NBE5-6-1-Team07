package com.grepp.team07.app.model.ordered;

import com.grepp.team07.app.model.ordered.dto.OrderedDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderedService {

    private final OrderedRepository orderedRepository;

    public List<OrderedDto> findAll() {
        return orderedRepository.selectAll();
    }
}
