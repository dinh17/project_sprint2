package com.example.jssport_back_end.service.impl;

import com.example.jssport_back_end.model.order.Orders;
import com.example.jssport_back_end.repository.IOrderRepository;
import com.example.jssport_back_end.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderService implements IOrderService {
    @Autowired
    private IOrderRepository iOrderRepository;

    @Override
    public void createCart(Long accountId) {
        iOrderRepository.createOrder(accountId);
    }

    @Override
    public Optional<Orders> findByAccountId(Long accountId) {
        return iOrderRepository.findByAccountId(accountId);
    }

    @Override
    public Optional<Orders> findById(Long orderId) {
        return iOrderRepository.findById(orderId);
    }

    @Override
    public void payAllByOrderId(Long orderId, String orderDate, String address, String phoneNumber, String note) {
        iOrderRepository.payAllByOrderId(orderId, orderDate, address, phoneNumber, note);
    }

    @Override
    public Page<Orders> findOrderPurchaseByAccountId(Long accountId, Pageable pageable) {
        return iOrderRepository.findOrderPurchaseByAccountId(accountId,pageable);
    }
}
