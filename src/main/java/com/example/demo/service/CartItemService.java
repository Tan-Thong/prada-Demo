package com.example.demo.service;

import com.example.demo.entity.CartItem;

public interface CartItemService {
    public CartItem getCartItemByProductId(int productId);
    public CartItem getCartItemById(int id);
    public boolean creatCartItem(CartItem cartItem);
    public boolean updateCartItem(CartItem cartItem);
    public boolean deleteCartItem(int id);
}
