package com.example.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_ID", nullable = false)
    private int id;

    @NotBlank(message = "Product name cannot be blank")
    @Size(max = 255, message = "Product name cannot exceed 255 characters")
    @Column(name = "product_name")
    private String name;

    @Positive(message = "Price must be greater than zero")
    private double price;
    private boolean gender;
    private String image;

    @Positive(message = "Stock must be greater than zero")
    private int stock;

    @NotBlank(message = "Material name cannot be blank")
    @Size(max = 255, message = "Material name cannot exceed 255 characters")
    private String material;
    @Column(length = 2000)
    private String description;
    @Column(name = "is_like", nullable = false, columnDefinition = "bit default 0")
    private boolean isLike;

    @ManyToOne
    @JoinColumn(name = "color_ID")
    private Color color;

    @ManyToOne
    @JoinColumn(name = "category_ID")
    private Category category;

    public Product() {
    }

    public Product(int id, String name, double price, boolean gender, String image, int stock, String material, String description, Color color, Category category) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.gender = gender;
        this.image = image;
        this.stock = stock;
        this.material = material;
        this.description = description;
        this.color = color;
        this.category = category;
    }

    public Product(int id, String name, double price, boolean gender, String image, int stock, String material, String description, boolean isLike, Color color, Category category) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.gender = gender;
        this.image = image;
        this.stock = stock;
        this.material = material;
        this.description = description;
        this.isLike = false;
        this.color = color;
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isLike() {
        return isLike;
    }

    public void setLike(boolean like) {
        isLike = like;
    }
}
