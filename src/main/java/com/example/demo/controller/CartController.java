package com.example.demo.controller;

import com.example.demo.entity.CartItem;
import com.example.demo.entity.Invoice;
import com.example.demo.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class CartController {
    @Autowired
    private CartService cartService;

    public Double totalPrice(List<CartItem> list) {
        double total = 0;
        for(CartItem item : list) {
            total += item.getPrice();
        }
        return total;
    }

    @GetMapping("/checkout")
    public String checkout(Model model) {
        Invoice invoice = new Invoice();
        model.addAttribute("invoice", invoice);
        // ========= List cart item ========== //
        List<CartItem> listCartItem = cartService.getAllCartItems();
        model.addAttribute("listCartItem", listCartItem);
        model.addAttribute("total", totalPrice(listCartItem));

        return "checkout";
    }
}
