package com.example.demo.service;

import com.example.demo.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

public interface CategoryService {
    public List<Category> searchCategoryByParentNameAndGenderNot(String parentName, int gender);
    public List<Category> findByIdNot(Integer id);
    public List<Category> getAll();
    public List<Category> getCategoryNoParent();
    public List<Category> getCategoryHaveParent();
    public Boolean create(Category category);
    public Category findById(int id);
    public boolean update(Category category);
    public boolean delete(int id);
    public List<Category> searchCategory(String keyWord);
    public Page<Category> getAll(int pageNo);
}
