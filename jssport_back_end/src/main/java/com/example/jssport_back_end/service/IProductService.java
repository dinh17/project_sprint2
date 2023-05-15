package com.example.jssport_back_end.service;


import com.example.jssport_back_end.dto.IProductDto;
import com.example.jssport_back_end.dto.ProductDto;
import com.example.jssport_back_end.model.product.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface IProductService {
    Page<IProductDto> getAllProduct(Pageable pageable);
    Optional<Product> findById(Long productId);
    Page<IProductDto> searchAllProductByName(Pageable pageable, String name);
    Page<IProductDto> searchByCategory(Long categoryId,Pageable pageable);
}
