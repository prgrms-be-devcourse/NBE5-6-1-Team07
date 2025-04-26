package com.grepp.team07.app.controller.web.customer;

import com.grepp.team07.app.controller.web.customer.form.CustomerEditForm;
import com.grepp.team07.app.model.customer.CustomerService;
import com.grepp.team07.app.model.customer.dto.CustomerDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("mypage")
public class CustomerController {
    private final CustomerService customerService;
    private final PasswordEncoder passwordEncoder;

    @GetMapping
    public String mypage(Authentication authentication, Model model){
        log.info("authentication : {}", authentication);
        String userId = authentication.getName();
        CustomerDto info =  customerService.findById(userId);
        model.addAttribute("info", info);
        return "mypage";
    }

    @GetMapping("edit")
    public String editMypage(CustomerEditForm form, Authentication authentication, Model model){
        String userId = authentication.getName();
        CustomerDto info =  customerService.findById(userId);
        form.setUserId(info.getUserId());
        form.setEmail(info.getEmail());
        form.setPassword(null);
        form.setAddress(info.getAddress());
        form.setPostCode(info.getPostCode());

        model.addAttribute("customerEditForm", form);
        model.addAttribute("info", info);
        return "mypageEdit";
    }

    @PostMapping("edit")
    public String editMypage(@Valid CustomerEditForm form, BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()) {
            return "redirect:/mypage/edit";
        }

        if (form.getPassword() != null && !form.getPassword().isEmpty()) {
            form.setPassword(passwordEncoder.encode(form.getPassword()));
        } else {
            form.setPassword(passwordEncoder.encode(form.getCheckpassword()));
        }

        customerService.edit(form.toDto());
        return "redirect:/mypage";
    }

    @PostMapping("check-password")
    @ResponseBody
    public Map<String, Boolean> checkPassword(@RequestBody Map<String, String> payload) {
        String userId = payload.get("userId");
        String password = payload.get("password");
        boolean valid = customerService.checkPassword(userId, password);
        return Collections.singletonMap("valid", valid);
    }
}
