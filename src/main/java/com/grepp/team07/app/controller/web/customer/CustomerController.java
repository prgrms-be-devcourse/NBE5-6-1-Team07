package com.grepp.team07.app.controller.web.customer;

import com.grepp.team07.app.controller.web.customer.form.CustomerEditForm;
import com.grepp.team07.app.model.member.CustomerService;
import com.grepp.team07.app.model.member.dto.CustomerDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("mypage")
public class CustomerController {
    private final CustomerService customerService;

    @GetMapping
    public String mypage(Model model){
        CustomerDto info =  customerService.findById("user01");
        model.addAttribute("info", info);
        return "mypage";
    }

    @GetMapping("edit")
    public String editMypage(Model model){
        CustomerDto info =  customerService.findById("user01");
        model.addAttribute("info", info);
        return "mypageEdit";
    }

    @PostMapping("edit")
    public String editMypage(@Valid CustomerEditForm form, Model model){
        customerService.edit(form.toDto());
        return "redirect:/mypageEdit";
    }
}
