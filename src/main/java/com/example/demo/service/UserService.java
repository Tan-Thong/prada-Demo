package com.example.demo.service;

import com.example.demo.entity.User;

public interface UserService {
    public User findByUserName(String userName);
    public User findByEmail(String email);
    public Boolean createUser(User user);
    public Boolean updateUser(User user);
    public User findById(int id);
}
