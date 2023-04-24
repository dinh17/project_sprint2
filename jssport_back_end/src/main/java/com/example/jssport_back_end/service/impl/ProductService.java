package com.example.jssport_back_end.service.impl;

import com.example.jssport_back_end.dto.IProductDto;

import com.example.jssport_back_end.repository.IProductRepository;
import com.example.jssport_back_end.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProductService implements IProductService {

    @Autowired
    IProductRepository iProductRepository;

    @Override
    public Page<IProductDto> searchAllProductByName(Pageable pageable, String name) {
        return iProductRepository.searchAllProductByName(pageable, name);
    }
}
