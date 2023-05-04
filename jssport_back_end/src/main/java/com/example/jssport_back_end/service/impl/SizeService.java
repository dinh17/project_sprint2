package com.example.jssport_back_end.service.impl;

import com.example.jssport_back_end.model.product.Size;
import com.example.jssport_back_end.repository.ISizeRepository;
import com.example.jssport_back_end.service.ISizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SizeService implements ISizeService {
    @Autowired
    ISizeRepository sizeRepository;

    @Override
    public List<Size> getAll() {
        return sizeRepository.findAll();
    }
}
