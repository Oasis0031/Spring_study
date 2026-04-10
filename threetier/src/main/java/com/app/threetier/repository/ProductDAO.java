package com.app.threetier.repository;

import com.app.threetier.domain.vo.ProductVO;
import com.app.threetier.mapper.ProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ProductDAO {
    private final ProductMapper productMapper;

    public void save(ProductVO productVO){
        productMapper.insert(productVO);
    }
}
