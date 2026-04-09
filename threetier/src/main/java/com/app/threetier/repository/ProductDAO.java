package com.app.threetier.repository;

import com.app.threetier.domain.vo.ProductVO;
import com.app.threetier.mapper.ProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ProductDAO {

    public final ProductMapper productMapper;

    public List<ProductVO> selectAll() {
        return productMapper.selectAll();
    }
}
