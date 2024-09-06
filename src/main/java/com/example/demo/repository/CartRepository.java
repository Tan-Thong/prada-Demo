package com.example.demo.repository;

import com.example.demo.entity.Cart;
import com.example.demo.entity.WishList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CartRepository extends JpaRepository<Cart, Integer> {
    @Query("SELECT c FROM Cart c")
    public Cart findSingleCart();
}
