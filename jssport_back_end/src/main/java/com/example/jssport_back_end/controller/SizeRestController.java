package com.example.jssport_back_end.controller;

import com.example.jssport_back_end.model.product.Size;
import com.example.jssport_back_end.service.ISizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/size")
@CrossOrigin("*")
public class SizeRestController {
    @Autowired
    ISizeService iSizeService;

    @GetMapping("/list")
    public ResponseEntity<List<Size>> getAllSize() {
        List<Size> sizeList = iSizeService.getAll();
        if (sizeList.isEmpty()) {
            return new ResponseEntity<>(sizeList, HttpStatus.BAD_REQUEST);
        }
        return  new ResponseEntity<>(sizeList,HttpStatus.OK);
    }
}
