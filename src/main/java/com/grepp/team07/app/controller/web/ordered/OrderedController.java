package com.grepp.team07.app.controller.web.ordered;

import com.grepp.team07.app.model.ordered.OrderedService;
import com.grepp.team07.app.model.ordered.dto.OrderedDto;
import com.grepp.team07.infra.exceptions.CommonException;
import com.grepp.team07.infra.payload.PageParam;
import com.grepp.team07.infra.response.PageResponse;
import jakarta.annotation.PostConstruct;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("admin")
public class OrderedController {

    private final OrderedService orderedService;

    @PostConstruct
    public void init() {
        log.info("✅ OrderedController 초기화됨");
    }

    @GetMapping("orders")
    public String list(@Valid
                       PageParam param,
                       BindingResult bindingResult,
                       Model model){

        Pageable pageable = PageRequest.of(param.getPage() - 1, param.getSize());
        Page<OrderedDto> page = orderedService.findPaged(pageable);

        if(param.getPage() != 1 && page.getContent().isEmpty()){
            throw new CommonException();
        }

        PageResponse<OrderedDto> response = new PageResponse<>("/admin/orders", page, 3);
        model.addAttribute("page", response);
        return "admin/admin-order-check";
    }
}
