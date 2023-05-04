package com.example.jssport_back_end.service;

import com.example.jssport_back_end.dto.orders.ICartListDto;

import java.util.List;

public interface IPurchaseHistoryService {
    void createCartItem(Long productId, Long orderId, Integer quantity);

    List<ICartListDto> getAllProductByOderById(Long orderId);
}
