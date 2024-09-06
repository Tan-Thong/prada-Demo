package com.example.demo.repository;

import com.example.demo.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CartItemRepository extends JpaRepository<CartItem, Integer> {
    @Query("select c from CartItem c where c.product.id = ?1")
    public CartItem getCartItemByProductId(int productId);
}
