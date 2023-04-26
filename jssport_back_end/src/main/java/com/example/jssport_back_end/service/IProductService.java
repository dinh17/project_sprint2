package com.example.jssport_back_end.service;


import com.example.jssport_back_end.model.product.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface IProductService {
    Page<Product> getAllProduct(Pageable pageable);
    Optional<Product> findById(Long productId);
}
