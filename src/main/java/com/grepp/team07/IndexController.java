package com.grepp.team07;

import com.grepp.team07.app.model.product.dto.ProductDto;
import com.grepp.team07.app.model.product.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/")
public class IndexController {

    private final ProductService productService;

    @GetMapping
    private String index(Model model){
        List<ProductDto> products = productService.bestProduct();
        model.addAttribute("products", products);
        return "index";
    }
}