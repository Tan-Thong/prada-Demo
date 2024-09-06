package com.example.demo.controller;

import com.example.demo.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/test-upload")
public class TestController {
    @Autowired
    private StorageService storageService;

    @GetMapping
    public String index() {
        return "test";
    }

    @PostMapping
    public String index(@RequestParam("image")MultipartFile file) {
        storageService.store(file);
        return "test";
    }
}
