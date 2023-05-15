package com.example.jssport_back_end.controller;


import com.example.jssport_back_end.model.product.Category;
import com.example.jssport_back_end.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
@CrossOrigin("*")
public class CategoryRestController {
    @Autowired
    private ICategoryService iCategoryService;

    @GetMapping("/list")
    public ResponseEntity<List<Category>> getAllCategory(){
        List<Category> categoryList= iCategoryService.findAllCategory();
        if(categoryList.isEmpty()){
            return new ResponseEntity<>(categoryList, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(categoryList,HttpStatus.OK);
    }

    @GetMapping("/categoryName/{categoryId}")
    public ResponseEntity<String> getCategoryName(@PathVariable Long categoryId){
        String categoryName= iCategoryService.getCategoryName(categoryId);
        if(categoryName==null){
            return new ResponseEntity<>( HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(categoryName,HttpStatus.OK);
    }
}
