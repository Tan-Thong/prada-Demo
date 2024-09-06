package com.example.demo.controller;

import com.example.demo.entity.Category;
import com.example.demo.entity.Color;
import com.example.demo.entity.Product;
import com.example.demo.service.CategoryService;
import com.example.demo.service.ColorService;
import com.example.demo.service.ProductService;
import com.example.demo.service.StorageService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ColorService colorService;
    @Autowired
    private StorageService storageService;


    @GetMapping("/product")
    public String index(Model model){
        List<Product> list = productService.getAll();
        model.addAttribute("list", list);
        return "admin/product/index";
    }

    @GetMapping("/add-product")
    public String add(Model model){
        List<Category> categoryList = categoryService.getCategoryHaveParent();
        model.addAttribute("categoryList", categoryList);
        List<Color> colorList = colorService.getAll();
        model.addAttribute("colorList", colorList);
        Product product = new Product();
        model.addAttribute("product", product);
        return "admin/product/add";
    }

    @PostMapping("/add-product")
    public String save(@ModelAttribute("product") @Valid Product product, BindingResult bindingResult, Model model, @RequestParam("loadImage") MultipartFile file){
        if(bindingResult.hasErrors()) {
            List<Category> categoryList = categoryService.getCategoryHaveParent();
            model.addAttribute("categoryList", categoryList);
            List<Color> colorList = colorService.getAll();
            model.addAttribute("colorList", colorList);
            model.addAttribute("product", product);
            return "admin/product/add";
        }

        storageService.store(file);
        String fileName = file.getOriginalFilename();
        product.setImage(fileName);
        product.setLike(false);
        if(productService.create(product)){
            return "redirect:/admin/product";
        } else {
            return "admin/product/add";
        }
    }

    @GetMapping("/edit-product/{id}")
    public String edit(Model model, @PathVariable("id") int id) {
        List<Category> categoryList = categoryService.getAll();
        model.addAttribute("categoryList", categoryList);
        List<Color> colorList = colorService.getAll();
        model.addAttribute("colorList", colorList);
        Product product = productService.findById(id);
        model.addAttribute("product", product);
        return "admin/product/edit";
    }

    @PostMapping("/edit-product")
    public String update(@ModelAttribute("product") @Valid Product product, BindingResult bindingResult, Model model, @RequestParam("loadImage") MultipartFile file){
        if(bindingResult.hasErrors()) {
            List<Category> categoryList = categoryService.getCategoryHaveParent();
            model.addAttribute("categoryList", categoryList);
            List<Color> colorList = colorService.getAll();
            model.addAttribute("colorList", colorList);
            model.addAttribute("product", product);
            return "admin/product/edit";
        }

        storageService.store(file);
        String fileName = file.getOriginalFilename();
        product.setImage(fileName);
        product.setLike(false);
        if(productService.update(product)){
            return "redirect:/admin/product";
        } else {
            return "admin/product/edit";
        }
    }

    @GetMapping("/delete-product/{id}")
    public String delete(@PathVariable("id") int id){
        if(productService.delete(id)){
            return "redirect:/admin/product";
        } else {
            return "redirect:/admin/product";
        }
    }
}
