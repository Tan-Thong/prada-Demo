package com.example.demo.service;

import com.example.demo.entity.Cart;
import com.example.demo.entity.CartItem;
import com.example.demo.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository cartRepository;

    @Override
    public Cart getCart() {
        Cart cart = cartRepository.findSingleCart();
        if (cart == null) {
            cart = new Cart();
            cartRepository.save(cart);
        }
        return cart;
    }

    @Override
    public boolean updateCart(Cart cart) {
        try {
            cartRepository.save(cart);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<CartItem> getAllCartItems() {
        return cartRepository.findSingleCart().getItems();
    }

    @Override
    public boolean addCartItem(CartItem cartItem) {
        Cart cart = cartRepository.findSingleCart();
        if(cart.getItems().add(cartItem)) {
            cartRepository.save(cart);
            return true;
        }
        return false;
    }

    @Override
    public boolean removeCartItem(CartItem cartItem) {
        Cart cart = cartRepository.findSingleCart();
        if(cart.getItems().remove(cartItem)) {
            cartRepository.save(cart);
            return true;
        }
        return false;
    }

    @Override
    public boolean removeAllCartItem() {
        Cart cart = cartRepository.findSingleCart();
        if (cart != null) {
            cart.getItems().clear(); // Xóa tất cả các mục trong giỏ hàng
            cartRepository.save(cart); // Lưu lại giỏ hàng sau khi xóa
            return true;
        }
        return false;
    }
}
