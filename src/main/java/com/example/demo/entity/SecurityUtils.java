package com.example.demo.entity;

import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtils {
    public static User getPrincipal() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof CustomUserDetails) {
            return ((CustomUserDetails) principal).getUser();
        }
        return null;
    }
}
