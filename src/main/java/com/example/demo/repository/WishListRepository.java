package com.example.demo.repository;

import com.example.demo.entity.WishList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface WishListRepository extends JpaRepository<WishList, Integer> {
    @Query("SELECT w FROM WishList w")
    public WishList findSingleWishList();
}
