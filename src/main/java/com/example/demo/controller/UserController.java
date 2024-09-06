package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/register")
    public String signUp(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "admin/sign-up";
    }

    @PostMapping("/register")
    public String createUser(@ModelAttribute("user") @Valid User user, BindingResult bindingResult, Model model, @RequestParam("confirmPassword") String confirmPassword){
        if(bindingResult.hasErrors()) {
            model.addAttribute("user", user);
            return "admin/sign-up";
        }

        // Kiểm tra mật khẩu và xác nhận mật khẩu
        if (!user.getPassword().equals(confirmPassword)) {
            bindingResult.rejectValue("password", "error.user", "Passwords do not match");
            model.addAttribute("user", user);
            return "admin/sign-up"; // Trả về trang đăng ký với lỗi
        }

        if(user.getPassword().equals(confirmPassword)) {
            if(userService.createUser(user)){
                return "admin/login";
            } else {
                return "redirect:/register";
            }
        }
        return "redirect:/register";
    }

    @PostMapping("/update-user")
    public String updateUser(HttpSession session, @RequestParam("userId") int id, @RequestParam("fullName") String fullName, @RequestParam("address") String address, @RequestParam("email") String email, @RequestParam("phone") String phone){
        // Cập nhật dữ liệu người dùng trong cơ sở dữ liệu
        User user = userService.findById(id);
        user.setFullName(fullName);
        user.setAddress(address);
        user.setEmail(email);
        user.setPhone(phone);

        if(userService.updateUser(user)) {
            // Cập nhật thông tin người dùng trong session
            session.setAttribute("user", user);
            return "redirect:/profile";
        }
        return "/error";
    }
}
