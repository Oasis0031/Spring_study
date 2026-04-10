package com.app.threetier.controller;

import com.app.threetier.domain.vo.ProductVO;
import com.app.threetier.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@Slf4j
@RequestMapping("/products/*")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/register")
    public void goToRegister(@ModelAttribute ProductVO productVO){;}

    @PostMapping("/register")
    public RedirectView registerProduct(ProductVO productVO){
        productService.registerProduct(productVO);
        return new RedirectView("/products/list");
    }
}
