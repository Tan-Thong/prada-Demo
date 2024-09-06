package com.example.demo.service;

import com.example.demo.entity.Color;
import com.example.demo.repository.ColorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ColorServiceImpl implements ColorService {
    @Autowired
    private ColorRepository colorRepository;

    @Override
    public List<Color> getAll() {
        return colorRepository.findAll();
    }

    @Override
    public Boolean create(Color color) {
        try {
            colorRepository.save(color);
            return true;
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Color findById(String id) {
        return colorRepository.findById(id).get();
    }

    @Override
    public boolean update(Color color) {
        try {
            colorRepository.save(color);
            return true;
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(String id) {
        try {
            colorRepository.delete(findById(id));
            return true;
        } catch(Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
