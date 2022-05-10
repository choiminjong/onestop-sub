package com.nexon.onestop.service.impl;

import com.nexon.onestop.domain.entity.Category;
import com.nexon.onestop.domain.entity.Role;
import com.nexon.onestop.repository.CategoryRepository;
import com.nexon.onestop.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    @Transactional
    public Page<Category> getPageCategory(String searchText, Pageable pageable) {
        return categoryRepository.findByCategoryNameContaining(searchText, pageable);
    }

}