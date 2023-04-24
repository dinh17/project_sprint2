package com.example.jssport_back_end.controller;


import com.example.jssport_back_end.dto.IProductDto;
import com.example.jssport_back_end.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/product")
@CrossOrigin("*")
public class ProductRestController {
    @Autowired
    private IProductService iProductService;
    @PostMapping("/page1")
    public ResponseEntity<Page<IProductDto>> getAllProduct(@PageableDefault(size = 4) Pageable pageable,
                                                           @RequestParam("name") String name,
                                                           @RequestBody String[] list){
        Page<IProductDto> productDtoPage = null;
        List<Integer> list1 = new ArrayList<>();
        for (String s: list) {
            list1.add(Integer.valueOf(s.split("-")[0]));
            list1.add(Integer.valueOf(s.split("-")[1]));
        }
        if(list1.size() == 0) {
            productDtoPage = iProductService.searchAllProductByName(pageable, name);
        }
        if(productDtoPage.getContent().isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(productDtoPage,HttpStatus.OK);
    }
}
