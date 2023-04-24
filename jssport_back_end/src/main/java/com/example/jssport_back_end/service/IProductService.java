package com.example.jssport_back_end.service;


import com.example.jssport_back_end.dto.IProductDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IProductService {
    Page<IProductDto> searchAllProductByName(Pageable pageable, String name);
}
