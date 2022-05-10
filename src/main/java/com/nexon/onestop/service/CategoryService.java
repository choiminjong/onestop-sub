package com.nexon.onestop.service;

import com.nexon.onestop.domain.entity.Category;
import com.nexon.onestop.domain.entity.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CategoryService {
    Page<Category> getPageCategory(String searchText , Pageable pageable);
    void createCategory(Category category);
    Category getCategory(long id);
    void deleteCategory(long id);
}
