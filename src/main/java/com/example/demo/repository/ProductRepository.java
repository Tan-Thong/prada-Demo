package com.example.demo.repository;

import com.example.demo.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    @Query("select p from Product p where p.name like %?1%")
    public List<Product> searchProduct(String keyWord);

    @Query("select p from Product p where p.category.name = ?1 and p.gender = ?2")
    public List<Product> getProductByCategoryAndGender(String category, boolean gender);

    @Query("select p from Product p where p.category.parent.name = ?1 and p.gender = ?2")
    public List<Product> getProductByCategoryParentAndGender(String category, boolean gender);
}
