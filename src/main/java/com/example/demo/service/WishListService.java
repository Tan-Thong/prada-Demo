package com.example.demo.service;

import com.example.demo.entity.Category;
import com.example.demo.entity.Product;
import com.example.demo.entity.WishList;

import java.util.List;
import java.util.Optional;

public interface WishListService {
    public WishList getWishList();
    public Optional<WishList> getWishListById(int id);
    public boolean update(WishList wishList);
    public List<Product> getAllItems();
    public boolean addItem(Product item);
    public boolean removeItem(Product itemId);
}
