package com.example.demo.service;

import com.example.demo.entity.Product;
import com.example.demo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> searchProductByName(String keyWord) {
        return productRepository.searchProduct(keyWord);
    }

    @Override
    public List<Product> getAll() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> getProductByCategoryAndGender(String category, boolean gender) {
        return productRepository.getProductByCategoryAndGender(category, gender);
    }

    @Override
    public List<Product> getProductByCategoryParentAndGender(String category, boolean gender) {
        return productRepository.getProductByCategoryParentAndGender(category, gender);
    }

    @Override
    public Boolean create(Product product) {
        try {
            productRepository.save(product);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Product findById(int id) {
        return productRepository.findById(id).get();
    }

    @Override
    public boolean update(Product product) {
        try {
            productRepository.save(product);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(int id) {
        try {
            productRepository.delete(findById(id));
            return true;
        } catch(Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
