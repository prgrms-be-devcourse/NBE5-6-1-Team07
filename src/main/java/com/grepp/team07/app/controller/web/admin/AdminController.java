package com.grepp.team07.app.controller.web.admin;

import com.grepp.team07.app.controller.web.admin.form.ProductInsertForm;
import com.grepp.team07.app.model.admin.AdminService;
import com.grepp.team07.app.model.ordered.OrderedService;
import com.grepp.team07.app.model.ordered.dto.OrderedDto;
import com.grepp.team07.app.model.product.dto.ProductDto;
import com.grepp.team07.infra.payload.PageParam;
import com.grepp.team07.infra.response.PageResponse;
import jakarta.validation.Valid;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@Slf4j
@RequestMapping("admin")
@RequiredArgsConstructor
public class AdminController {
    private final AdminService adminService;
    private final OrderedService orderedService;

    @GetMapping("orders")
    public String list(@Valid
    PageParam param,
        BindingResult bindingResult,
        Model model){

        Pageable pageable = PageRequest.of(param.getPage() - 1, param.getSize());
        Page<OrderedDto> page = orderedService.findPaged(pageable);

//        if(param.getPage() != 1 && page.getContent().isEmpty()){
//            throw new CommonException();
//        }

        PageResponse<OrderedDto> response = new PageResponse<>("/admin/orders", page, 3);
        model.addAttribute("page", response);
        return "admin/admin-order-check";
    }

    @GetMapping("admin-product")
    public String list(
        @RequestParam(required = false) Integer id,
        @RequestParam(required = false) String keyword,
        Model model
    ) {
        List<ProductDto> adminProducts;
        if (keyword != null && !keyword.trim().isEmpty()) {
            adminProducts = adminService.searchByKeyword(keyword);
            model.addAttribute("keyword", keyword);
        } else {
            adminProducts = adminService.findAll();
        }

        model.addAttribute("adminProducts", adminProducts);

        if (id != null) {
            ProductDto adminProduct = adminService.findById(id);
            model.addAttribute("productInsertForm", adminProduct);
        } else {
            model.addAttribute("productInsertForm", new ProductInsertForm());
        }

        return "admin/admin-product";
    }


    @PostMapping("admin-product")
    public String insertOrUpdate(
        @Valid ProductInsertForm form,
        BindingResult bindingResult,
        Model model
    ) {
        if (bindingResult.hasErrors()) {
            return "admin/admin-product";
        }

        if (form.getProductId() != null && form.getProductId() > 0) {
            adminService.updateProduct(form.getThumbnail(), form.fromForm());
        } else {
            adminService.insertProduct(form.getThumbnail(), form.toDto());
        }

        return "redirect:/admin/admin-product";
    }

    @PostMapping("admin-product/action")
    public String processAction(
        @RequestParam Integer id,
        @RequestParam String action,
        @RequestParam(required = false) String keyword
    ) {
        switch (action) {
            case "increment":
                adminService.incrementCount(id);
                break;
            case "decrement":
                adminService.decrementCount(id);
                break;
            case "delete":
                adminService.delete(id);
                return "redirect:/admin/admin-product";
            default:
                break;
        }

        if (keyword != null && !keyword.trim().isEmpty()) {
            return "redirect:/admin/admin-product?keyword=" + URLEncoder.encode(keyword, StandardCharsets.UTF_8);
        }
        return "redirect:/admin/admin-product";
    }
}
