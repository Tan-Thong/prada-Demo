package com.example.demo.service;

import com.example.demo.entity.*;
import com.example.demo.repository.CartRepository;
import com.example.demo.repository.InvoiceRepository;
import com.example.demo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class InvoiceServiceImpl implements InvoiceService {
    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Invoice getById(int id) {
        return invoiceRepository.findById(id).get();
    }

    @Override
    public Boolean createInvoice(Invoice invoice) {
        Cart cart = cartRepository.findSingleCart();
        if (cart == null || cart.getItems().isEmpty()) {
            throw new RuntimeException("Cart is empty");
        }

        invoice.setInvoiceDate(new Date());

        double totalAmount = 0.0;
        for (CartItem item : cart.getItems()) {
            InvoiceItem invoiceItem = new InvoiceItem();
            invoiceItem.setInvoice(invoice);
            invoiceItem.setProduct(item.getProduct());
            invoiceItem.setQuantity(item.getQuantity());
            invoiceItem.setPrice(item.getProduct().getPrice() * item.getQuantity());

            invoice.getInvoiceItems().add(invoiceItem);
            totalAmount += invoiceItem.getPrice();

            // Update the product stock
            Product product = item.getProduct();
            product.setStock(product.getStock() - item.getQuantity());
            productRepository.save(product);
        }

        invoice.setTotalAmount(totalAmount);
        try {
            invoiceRepository.save(invoice);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Boolean deleteInvoice(Invoice invoice) {
        return null;
    }

    @Override
    public List<Invoice> findInvoiceByUser(User user) {
        return invoiceRepository.findInvoiceByUser(user);
    }
}
