package com.grepp.team07.app.controller.web.ordered;

import com.grepp.team07.app.model.ordered.OrderedService;
import com.grepp.team07.app.model.ordered.dto.OrderedDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("ordered")
public class OrderedController {

    private final OrderedService orderedService;

    @GetMapping("list")
    public String signup(Model model){
        List<OrderedDto> orders = orderedService.findAll();
        model.addAttribute("orders", orders);
        return "adminOrderCheck";
    }
}
