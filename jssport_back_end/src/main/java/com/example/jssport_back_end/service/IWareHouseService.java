package com.example.jssport_back_end.service;

import com.example.jssport_back_end.dto.IWareHouseDto;

import java.util.List;

public interface IWareHouseService {
    List<IWareHouseDto> getAll();

    IWareHouseDto findByProductId(Long productId);

    void updateQuantityProduct(Long orderId );

}
