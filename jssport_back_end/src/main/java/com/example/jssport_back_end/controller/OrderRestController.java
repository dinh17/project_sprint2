package com.example.jssport_back_end.controller;

import com.example.jssport_back_end.dto.orders.*;
import com.example.jssport_back_end.model.order.Orders;
import com.example.jssport_back_end.model.order.PurchaseHistory;
import com.example.jssport_back_end.service.IOrderService;
import com.example.jssport_back_end.service.IPurchaseHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order")
@CrossOrigin("*")
public class OrderRestController {
    @Autowired
    private IPurchaseHistoryService iPurchaseHistoryService;
    @Autowired
    private IOrderService iOrderService;

    @GetMapping("/detail/{accountId}")
    public ResponseEntity<Orders> findById(@PathVariable Long accountId) {
        Orders order = iOrderService.findByAccountId(accountId).orElse(null);
        if (order == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(order, HttpStatus.OK);
    }
    @GetMapping("/order-purchase/{accountId}")
    public ResponseEntity<Page<Orders>> findOrderPurchaseByAccountId(@PathVariable Long accountId, @PageableDefault(size = 4) Pageable pageable) {
        Page<Orders> ordersList = iOrderService.findOrderPurchaseByAccountId(accountId, pageable);
        if (ordersList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(ordersList, HttpStatus.OK);
    }

    @GetMapping("/purchase-history/{orderId}")
    public ResponseEntity<List<ICartListDto>> getAllPurchaseHistory(@PathVariable Long orderId) {
        List<ICartListDto> purchaseHistoryList = iPurchaseHistoryService.getPurchaseHistoriesByOrderId(orderId);
        if (purchaseHistoryList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(purchaseHistoryList, HttpStatus.OK);
    }


    @GetMapping("/total/{orderId}")
    public ResponseEntity<ITotalDto> getTotal(@PathVariable Long orderId) {
        ITotalDto totalDto = iPurchaseHistoryService.getTotal(orderId);
        if (totalDto == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(totalDto, HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteCartItemById(@RequestParam Long productId, @RequestParam Long orderId) {
        PurchaseHistory purchaseHistory = iPurchaseHistoryService.findCartItemById(orderId, productId);
        if (purchaseHistory == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        iPurchaseHistoryService.deleteCartItem(orderId, productId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PostMapping("/order")
    public ResponseEntity<?> order(@RequestBody OrderDto orderDto) {
        Orders order = iOrderService.findByAccountId(orderDto.getAccountId()).orElse(null);
        if (order == null) {
            iOrderService.createCart(orderDto.getAccountId());
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/cart")
    public ResponseEntity<?> addToCart(@RequestBody CartDto cartDto) {
        iPurchaseHistoryService.createCartItem(cartDto.getOrderId(), cartDto.getProductId(), cartDto.getQuantity());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/updateQuantity")
    public ResponseEntity<?> updateQuantityCartItem(@RequestBody CartDto cartDto) {
        iPurchaseHistoryService.updateQuantityCartItem(cartDto.getOrderId(), cartDto.getProductId(), cartDto.getQuantity());
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PutMapping("/pay")
    @PreAuthorize("hasAuthority('ROLE_CUSTOMER')")
    public ResponseEntity<?> payAllByOrderId(@RequestBody OrderPaymentDto orderPaymentDto) {
        Orders orders = iOrderService.findById(orderPaymentDto.getOrderId()).orElse(null);
        if (orders == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        iOrderService.payAllByOrderId(orderPaymentDto.getOrderId(), orderPaymentDto.getOrderDate(),
                orderPaymentDto.getAddress(), orderPaymentDto.getPhoneNumber(), orderPaymentDto.getNote());
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping("/list/{orderId}")
    public ResponseEntity<List<ICartListDto>> getAllCart(@PathVariable Long orderId) {
        List<ICartListDto> cartListDtos = iPurchaseHistoryService.getAllProductByOrderId(orderId);
        return new ResponseEntity<>(cartListDtos, HttpStatus.OK);
    }



}
