package com.example.jssport_back_end.service;

import com.example.jssport_back_end.dto.orders.ICartListDto;
import com.example.jssport_back_end.dto.orders.IOrderPaymentDto;
import com.example.jssport_back_end.dto.orders.ITotalDto;
import com.example.jssport_back_end.dto.orders.OrderPaymentDto;
import com.example.jssport_back_end.model.order.PurchaseHistory;

import java.util.List;

public interface IPurchaseHistoryService {
    ITotalDto getTotal(Long orderId);
    void createCartItem(Long orderId,Long productId, Integer quantity);
    PurchaseHistory findCartItemById(Long orderId, Long productId);
    List<ICartListDto> getAllProductByOderById(Long orderId);
    List<ICartListDto> getPurchaseHistoriesByOrderId( Long orderId);
    void updateQuantityCartItem( Long orderId,Long productId, Integer quantity);
    List<ICartListDto> getAllProductByOrderId(Long orderId);
    void deleteCartItem(Long orderId, Long productId);
    List<OrderPaymentDto> getAllOrderByAccountId(Long accountId);
}
