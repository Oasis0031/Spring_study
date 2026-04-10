package com.app.threetier.service;

import com.app.threetier.domain.vo.ProductVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class ProductServiceTests {

    @Autowired
    private ProductService productService;

    @Test
    public void registerProductTest(){
        ProductVO productVO = new ProductVO();
        productVO.setProductName("규혁이의 마스크");
        productVO.setProductPrice(10000);
        productVO.setProductBrand("뀨잉");
        productVO.setProductStock(50);
        productService.registerProduct(productVO);
    }

}
