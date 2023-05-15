package com.example.jssport_back_end.service.impl;

import com.example.jssport_back_end.dto.IBestProductDto;
import com.example.jssport_back_end.dto.orders.*;
import com.example.jssport_back_end.model.order.PurchaseHistory;
import com.example.jssport_back_end.repository.IPurchaseHistoryRepository;
import com.example.jssport_back_end.service.IPurchaseHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PurchaseHistoryService implements IPurchaseHistoryService {
    @Autowired
    IPurchaseHistoryRepository iPurchaseHistoryRepository;

    @Override
    public List<ICartListDto> getPurchaseHistoriesByOrderId(Long orderId) {
        return iPurchaseHistoryRepository.getPurchaseHistoriesByOrderId(orderId);
    }

    @Override
    public ITotalDto getTotal(Long orderId) {
        return iPurchaseHistoryRepository.getTotal(orderId);
    }

    @Override
    public void createCartItem(Long orderId, Long productId, Integer quantity) {
        List<ICartListDto> cartListDtos = iPurchaseHistoryRepository.getAllProductByOderById(orderId);
        for (ICartListDto cart : cartListDtos) {
            if (cart.getProductId().equals(productId)) {
                Integer newQuantity = quantity + cart.getQuantity();
                iPurchaseHistoryRepository.updateCartItem(orderId, productId, newQuantity);
                return;
            }
        }
        iPurchaseHistoryRepository.createCartItem(orderId, productId, quantity);
    }

    @Override
    public PurchaseHistory findCartItemById(Long orderId, Long productId) {
        return iPurchaseHistoryRepository.findCartItemById(orderId, productId);
    }

    @Override
    public List<ICartListDto> getAllProductByOderById(Long orderId) {
        return iPurchaseHistoryRepository.getAllProductByOderById(orderId);
    }

    @Override
    public void updateQuantityCartItem( Long orderId,Long productId ,Integer quantity) {
        iPurchaseHistoryRepository.updateCartItem( orderId,productId, quantity);
    }

    @Override
    public List<ICartListDto> getAllProductByOrderId(Long orderId) {
        return iPurchaseHistoryRepository.getAllProductByOderById(orderId);
    }

    @Override
    public void deleteCartItem(Long orderId, Long productId) {
    iPurchaseHistoryRepository.deleteCartItem(orderId,productId);
    }

    @Override
    public List<ICartListDto> getAllOrderByAccountId(Long orderId) {
        return iPurchaseHistoryRepository.getPurchaseHistoriesByOrderId(orderId);
    }

    @Override
    public Page<IBestProductDto> getBestProduct(Pageable pageable) {
        return iPurchaseHistoryRepository.getBestProduct(pageable);
    }
}
