package com.example.demo.service;

import com.example.demo.entity.Category;
import com.example.demo.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService{
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> searchCategoryByParentNameAndGenderNot(String parentName, int gender) {
        return categoryRepository.searchCategoryByParentNameAndGenderNot(parentName, gender);
    }

    @Override
    public List<Category> findByIdNot(Integer id) {
        return categoryRepository.findByIdNot(id);
    }

    @Override
    public List<Category> getAll() {
        return categoryRepository.findAll();
    }

    @Override
    public List<Category> getCategoryNoParent() {
        return categoryRepository.getCategoryNoParent();
    }

    @Override
    public List<Category> getCategoryHaveParent() {
        return categoryRepository.getCategoryHaveParent();
    }

    @Override
    public Boolean create(Category category) {
        try {
            categoryRepository.save(category);
            return true;
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Category findById(int id) {
        return categoryRepository.findById(id).get();
    }

    @Override
    public boolean update(Category category) {
        try {
            categoryRepository.save(category);
            return true;
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(int id) {
        try {
            categoryRepository.delete(findById(id));
            return true;
        } catch(Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Category> searchCategory(String keyWord) {
        return categoryRepository.searchCategory(keyWord);
    }

    @Override
    public Page<Category> getAll(int pageNo) {
        Pageable pageable = PageRequest.of(pageNo-1, 2);
        return categoryRepository.findAll(pageable);
    }
}
