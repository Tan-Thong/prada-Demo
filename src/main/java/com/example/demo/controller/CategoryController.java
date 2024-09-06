package com.example.demo.controller;


import com.example.demo.entity.Category;
import com.example.demo.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/category")
    public String index(Model model, @Param("keyWord") String keyWord, @RequestParam(value = "pageNo", defaultValue = "1") int pageNo ){
        List<Category> list = categoryService.getAll();
        if(keyWord != null){
            list = categoryService.searchCategory(keyWord);
            model.addAttribute("keyWord", keyWord);
        }
//        Page<Category> list = categoryService.getAll(pageNo);
        model.addAttribute("list", list);
        return "admin/category/index";
    }

    @GetMapping("/add-category")
    public String add(Model model){
        Category category = new Category();
        model.addAttribute("category", category);
        List<Category> list = categoryService.getCategoryNoParent();
        model.addAttribute("list", list);
        return "admin/category/add";
    }

    @PostMapping("/add-category")
    public String add(@ModelAttribute("category") @Valid Category category, BindingResult bindingResult, @RequestParam(required = false) Integer parentId, Model model){
        if (parentId != null) {
            Category parent = categoryService.findById(parentId);
            category.setParent(parent);
        }

        // Kiểm tra lỗi validation
        if (bindingResult.hasErrors()) {
            // Trả lại view form nếu có lỗi, cùng với các thông tin đã nhập
            List<Category> list = categoryService.getCategoryNoParent();
            model.addAttribute("list", list);
            model.addAttribute("category", category);
            return "admin/category/add";
        }

        if(categoryService.create(category)){
            return "redirect:/admin/category";
        } else {
            return "admin/category/add";
        }
    }

    @GetMapping("/edit-category/{id}")
    public String edit(Model model, @PathVariable("id") int id){
        Category category = categoryService.findById(id);
        model.addAttribute("category", category);
        List<Category> list = categoryService.findByIdNot(id);
        model.addAttribute("list", list);
        return "admin/category/edit";
    }

    @PostMapping("/edit-category")
    public String update(@ModelAttribute("category") @Valid Category category, BindingResult bindingResult, Model model, @RequestParam(required = false) Integer parentId){
        // Kiểm tra lỗi validation
        if (bindingResult.hasErrors()) {
            // Trả lại view form nếu có lỗi, cùng với các thông tin đã nhập
            List<Category> list = categoryService.getCategoryNoParent();
            model.addAttribute("list", list);
            model.addAttribute("category", category);
            return "admin/category/edit";
        }

        if (parentId != null) {
            Category parent = categoryService.findById(parentId);
            category.setParent(parent);
        }
        if(categoryService.update(category)){
            return "redirect:/admin/category";
        } else {
            return "admin/category/edit";
        }
    }

    @GetMapping("/delete-category/{id}")
    public String delete(@PathVariable("id") int id){
        if(categoryService.delete(id)){
            return "redirect:/admin/category";
        } else {
            return "redirect:/admin/category";
        }
    }
}
