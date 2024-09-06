package com.example.demo.service;

import com.example.demo.entity.Product;

import java.util.List;

public interface ProductService {
    public List<Product> searchProductByName(String keyWord);
    public List<Product> getAll();
    public List<Product> getProductByCategoryAndGender(String category, boolean gender);
    public List<Product> getProductByCategoryParentAndGender(String category, boolean gender);
    public Boolean create(Product product);
    public Product findById(int id);
    public boolean update(Product product);
    public boolean delete(int id);
}
