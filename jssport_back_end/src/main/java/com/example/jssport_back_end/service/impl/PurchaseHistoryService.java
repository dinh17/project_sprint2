package com.example.jssport_back_end.service.impl;

import com.example.jssport_back_end.dto.orders.ICartListDto;
import com.example.jssport_back_end.repository.IPurchaseHistoryRepository;
import com.example.jssport_back_end.service.IPurchaseHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PurchaseHistoryService implements IPurchaseHistoryService {
    @Autowired
    IPurchaseHistoryRepository iPurchaseHistoryRepository;

    @Override
    public void createCartItem(Long productId, Long orderId, Integer quantity) {
        List<ICartListDto>cartListDtos = iPurchaseHistoryRepository.getAllProductByOderById(orderId);
        for (ICartListDto cart : cartListDtos) {
            Integer newQuantity
        }
    }

    @Override
    public List<ICartListDto> getAllProductByOderById(Long orderId) {
        return iPurchaseHistoryRepository.getAllProductByOderById(orderId);
    }
}
