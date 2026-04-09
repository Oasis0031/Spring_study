package com.app.threetier;

import com.app.threetier.controller.ProductController;
import com.app.threetier.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class ProductServiceTests {
    @Autowired
    private ProductService productService;

    @Test
    public void selectAllTest() {
        productService.selectAll();
    }
}
