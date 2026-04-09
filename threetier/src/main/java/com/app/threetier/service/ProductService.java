package com.app.threetier.service;

import com.app.threetier.domain.vo.ProductVO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {

    public List<ProductVO> selectAll();

}
