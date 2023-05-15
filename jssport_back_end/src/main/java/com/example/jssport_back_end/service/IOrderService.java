package com.example.jssport_back_end.service;

import com.example.jssport_back_end.model.order.Orders;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface IOrderService {
    void createCart(Long accountId);

    Optional<Orders> findByAccountId(Long accountId);

    Optional<Orders> findById(Long orderId);
    void payAllByOrderId(Long orderId, String orderDate, String address, String phoneNumber, String note);
    Page<Orders> findOrderPurchaseByAccountId(Long accountId, Pageable pageable);
}
