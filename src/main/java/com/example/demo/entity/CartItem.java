package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "cart_items")
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_item_ID", nullable = false)
    private int id;

    @ManyToOne
    @JoinColumn(name = "product_ID", nullable = false)
    private Product product;

    @ManyToOne
    @JoinColumn(name = "cart_ID", nullable = false)
    private Cart cart;

    private int quantity;

    private double price;

    public CartItem() {
    }

    public CartItem(Product product, Cart cart, int quantity) {
        this.product = product;
        this.cart = cart;
        this.quantity = quantity;
    }

    public CartItem(Product product, Cart cart, int quantity, double price) {
        this.product = product;
        this.cart = cart;
        this.quantity = quantity;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
