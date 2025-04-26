package com.grepp.team07.app.controller.web.order;

import com.grepp.team07.app.model.member.dto.Member;
import com.grepp.team07.app.model.member.repository.MemberRepository;
import com.grepp.team07.app.model.ordered.OrderedService;
import com.grepp.team07.app.model.ordered.dto.OrderedDto;
import com.grepp.team07.infra.payload.PageParam;
import com.grepp.team07.infra.response.PageResponse;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("order")
public class OrderController {
    private final OrderedService orderedService;
    private final MemberRepository memberRepository;

    @GetMapping("list")
    public String list(
        @Valid PageParam param,
        @RequestParam(required = false) String email,
        Authentication authentication,
        Model model
    ) {
        Pageable pageable = PageRequest.of(param.getPage() - 1, param.getSize());
        Page<OrderedDto> page;

        if (email == null && authentication != null && authentication.isAuthenticated()) {
            String userId = authentication.getName();
            Member member = memberRepository.selectByUserId(userId)
                .orElseThrow(() -> new UsernameNotFoundException(userId));
            email = member.getEmail();
            page = orderedService.searchByMemberEmail(pageable, email);
        } else {
            page = orderedService.searchByEmail(pageable, email);
        }

        PageResponse<OrderedDto> response = new PageResponse<>("/order/list", page, 3);

        model.addAttribute("email", email);
        model.addAttribute("page", response);

        return "order/order-list";
    }
}
