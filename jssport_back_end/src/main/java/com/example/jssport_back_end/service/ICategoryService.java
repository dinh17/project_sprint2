package com.example.jssport_back_end.service;


import com.example.jssport_back_end.model.product.Category;

import java.util.List;

public interface ICategoryService {

    List<Category> findAllCategory();

    String getCategoryName( Long categoryId);
}
