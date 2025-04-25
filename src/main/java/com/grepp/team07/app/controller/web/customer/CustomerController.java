package com.grepp.team07.app.controller.web.customer;

import com.grepp.team07.app.controller.web.customer.form.CustomerEditForm;
import com.grepp.team07.app.model.customer.CustomerService;
import com.grepp.team07.app.model.customer.dto.CustomerDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
    public String editMypage(CustomerEditForm form, Model model){
        CustomerDto info =  customerService.findById("user01");
        form.setUserId(info.getUserId());
        form.setEmail(info.getEmail());
        form.setPassword(info.getPassword());
        form.setAddress(info.getAddress());
        form.setPostCode(info.getPostCode());

        model.addAttribute("customerEditform", form);
        model.addAttribute("info", info);
        return "mypageEdit";
    }

    @PostMapping("edit")
    public String editMypage(@Valid CustomerEditForm form, BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()) {
            return "redirect:/";
        }
        customerService.edit(form.toDto());
        return "redirect:/mypage";
    }
}
