package com.example.demo.service;

import com.example.demo.entity.CartItem;
import com.example.demo.repository.CartItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartItemServiceImpl implements CartItemService {
    @Autowired
    private CartItemRepository cartItemRepository;

    @Override
    public CartItem getCartItemByProductId(int productId) {
        return cartItemRepository.getCartItemByProductId(productId);
    }

    @Override
    public CartItem getCartItemById(int id) {
        return cartItemRepository.findById(id).get();
    }

    @Override
    public boolean creatCartItem(CartItem cartItem) {
        try {
            cartItemRepository.save(cartItem);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updateCartItem(CartItem cartItem) {
        try {
            cartItemRepository.save(cartItem);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteCartItem(int id) {
        try {
            cartItemRepository.delete(getCartItemById(id));
            return true;
        } catch(Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
