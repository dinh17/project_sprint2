package com.example.jssport_back_end.controller;

import com.example.jssport_back_end.dto.orders.CartDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/order")
@CrossOrigin("*")
public class OrderRestController {
    @PostMapping("/cart")
    public ResponseEntity<?> addToCart(@RequestBody CartDto cartDto) {

    }

}
