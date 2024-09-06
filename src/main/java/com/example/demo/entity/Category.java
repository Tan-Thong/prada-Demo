package com.example.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_ID", nullable = false)
    private int id;
    @Column(name = "category_name")
    @Pattern(regexp = "^([A-Z][a-z]*)(?:\\s[A-Za-z]+)*$", message = "Capitalize the first letter and not null*") // In hoa chữ cái đầu, cách nhau bằng khoảng trắng
    private String name;

    private int gender;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "parent_id")
    private Category parent;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Category> subcategories = new HashSet<>();

    @OneToMany(mappedBy = "category")
    private Set<Product> products;

    public Category() {
    }

    public Category(String name) {
        this.name = name;
    }

    public Category(String name, int gender, Category parent) {
        this.name = name;
        this.gender = gender;
        this.parent = parent;
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

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public Category getParent() {
        return parent;
    }

    public void setParent(Category parent) {
        this.parent = parent;
    }

    public Set<Category> getSubcategories() {
        return subcategories;
    }

    public void setSubcategories(Set<Category> subcategories) {
        this.subcategories = subcategories;
    }

    public void addSubcategory(Category subcategory) {
        subcategories.add(subcategory);
        subcategory.setParent(this);
    }

    public void removeSubcategory(Category subcategory) {
        subcategories.remove(subcategory);
        subcategory.setParent(null);
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }
}
