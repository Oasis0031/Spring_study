package com.app.threetier.domain.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class ProductDTO {
    private Long id;
    private String productName;
    private Long productPrice;
    private Long productStock;
    private String productBrand;
}
