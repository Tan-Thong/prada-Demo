package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "invoice_items")
public class InvoiceItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "invoice_item_ID", nullable = false)
    private int id;

    @ManyToOne
    @JoinColumn(name = "invoice_ID", nullable = false)
    private Invoice invoice;

    @ManyToOne
    @JoinColumn(name = "product_ID", nullable = false)
    private Product product;

    private int quantity;
    private double price;

    public InvoiceItem() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
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

