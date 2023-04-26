package com.example.jssport_back_end.service.impl;

import com.example.jssport_back_end.dto.IProductDto;

import com.example.jssport_back_end.model.product.Product;
import com.example.jssport_back_end.repository.IProductRepository;
import com.example.jssport_back_end.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService implements IProductService {
 @Autowired
 IProductRepository iProductRepository;
    @Override
    public Page<Product> getAllProduct(Pageable pageable) {
        return iProductRepository.getAllProduct(pageable);
    }

    @Override
    public Optional<Product> findById(Long productId) {
        return iProductRepository.findById(productId);
    }
}
