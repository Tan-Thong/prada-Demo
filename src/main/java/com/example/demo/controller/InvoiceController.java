package com.example.demo.controller;

import com.example.demo.entity.Invoice;
import com.example.demo.entity.SecurityUtils;
import com.example.demo.entity.User;
import com.example.demo.service.CartService;
import com.example.demo.service.InvoiceService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class InvoiceController {
    @Autowired
    private InvoiceService invoiceService;

    @Autowired
    private CartService cartService;

    @Autowired
    private UserService userService;

    @PostMapping("/create-invoice")
    public String createInvoice(@ModelAttribute("invoice") Invoice invoice) {
        User user = SecurityUtils.getPrincipal();
        invoice.setUser(user);
        if(invoiceService.createInvoice(invoice)) {
            cartService.removeAllCartItem();
            return "redirect:/";
        }
        return "redirect:/checkout";
    }
}
