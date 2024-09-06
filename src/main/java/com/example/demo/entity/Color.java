package com.example.demo.entity;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "colors")
public class Color {
    @Id
    @Column(name = "color_ID", nullable = false)
    private String id;
    @Column(name = "color_name")
    private String name;

    @OneToMany(mappedBy = "color", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Product> products;

    public Color() {
    }

    public Color(String id, String name, Set<Product> products) {
        this.id = id;
        this.name = name;
        this.products = products;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }
}
