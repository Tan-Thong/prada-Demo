package com.example.demo.service;

import com.example.demo.entity.Cart;
import com.example.demo.entity.CartItem;
import com.example.demo.entity.Product;
import com.example.demo.repository.CartRepository;

import java.util.List;

public interface CartService {
    public Cart getCart();
    public boolean updateCart(Cart cart);
    public List<CartItem> getAllCartItems();
    public boolean addCartItem(CartItem cartItem);
    public boolean removeCartItem(CartItem cartItem);
    public boolean removeAllCartItem();
}
