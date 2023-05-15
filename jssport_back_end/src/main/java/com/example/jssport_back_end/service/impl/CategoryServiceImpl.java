package com.example.jssport_back_end.service.impl;


import com.example.jssport_back_end.model.product.Category;
import com.example.jssport_back_end.repository.ICategoryRepository;
import com.example.jssport_back_end.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements ICategoryService {

    @Autowired
    private ICategoryRepository iCategoryRepository;

    @Override
    public List<Category> findAllCategory() {
        return iCategoryRepository.findAllCategory();
    }

    @Override
    public String getCategoryName(Long categoryId) {
        return iCategoryRepository.getCategoryName(categoryId);
    }
}
