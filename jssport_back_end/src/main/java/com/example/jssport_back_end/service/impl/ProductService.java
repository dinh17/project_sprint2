package com.example.jssport_back_end.service.impl;

import com.example.jssport_back_end.dto.IProductDto;

import com.example.jssport_back_end.dto.ProductDto;
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
    public Page<IProductDto> getAllProduct(Pageable pageable) {
        return iProductRepository.getAllProduct(pageable);
    }

    @Override
    public Optional<Product> findById(Long productId) {
        return iProductRepository.findById(productId);
    }

    @Override
    public Page<IProductDto> searchAllProductByName(Pageable pageable, String name) {
        return iProductRepository.searchAllProductByName(pageable,name);
    }

    @Override
    public Page<IProductDto> searchByCategory(Long categoryId, Pageable pageable) {
        return iProductRepository.searchByCategory(categoryId,pageable);
    }
}
