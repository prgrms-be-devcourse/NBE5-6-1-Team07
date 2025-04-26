package com.grepp.team07.app.model.ordered;

import com.grepp.team07.app.model.ordered.dto.OrderedDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
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

    public Page<OrderedDto> findPaged(Pageable pageable) {
        int offset = (int) pageable.getOffset();
        int size = pageable.getPageSize();
        List<OrderedDto> result =  orderedRepository.findPaged(size, offset);
        int total = orderedRepository.countAllOrders();

        return new PageImpl<>(result, pageable, total);
    }

    public Page<OrderedDto> searchByEmail(Pageable pageable, String email) {
        int offset = (int) pageable.getOffset();
        int size = pageable.getPageSize();
        List<OrderedDto> result = orderedRepository.searchByEmail(size, offset, email);
        int total = orderedRepository.countEmailOrders(email);

        return new PageImpl<>(result, pageable, total);
    }

    public Page<OrderedDto> searchByMemberEmail(Pageable pageable, String email) {
        int offset = (int) pageable.getOffset();
        int size = pageable.getPageSize();
        List<OrderedDto> result =  orderedRepository.searchByMemberEmail(size, offset, email);
        int total = orderedRepository.countMemberEmailOrders(email);

        return new PageImpl<>(result, pageable, total);
    }
}
