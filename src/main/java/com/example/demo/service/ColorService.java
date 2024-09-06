package com.example.demo.service;

import com.example.demo.entity.Color;

import java.util.List;

public interface ColorService {
    public List<Color> getAll();
    public Boolean create(Color color);
    public Color findById(String id);
    public boolean update(Color color);
    public boolean delete(String id);
}
