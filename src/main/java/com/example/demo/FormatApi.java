package com.example.demo;

import org.springframework.stereotype.Component;

@Component
public class FormatApi {
    public String formatCategoryName(String name) {
        return name.toLowerCase().replace(" ", "-");
    }
}
