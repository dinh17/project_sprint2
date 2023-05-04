package com.example.jssport_back_end.service.impl;

import com.example.jssport_back_end.dto.IWareHouseDto;
import com.example.jssport_back_end.repository.IWareHouseRepository;
import com.example.jssport_back_end.service.IWareHouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class WareHouseService implements IWareHouseService {
    @Autowired
    IWareHouseRepository wareHouseRepository;

    @Override
    public List<IWareHouseDto> getAll() {
        return wareHouseRepository.getAll();
    }

    @Override
    public IWareHouseDto findByProductId(Long productId) {
        return wareHouseRepository.findByProductId(productId);
    }
}
