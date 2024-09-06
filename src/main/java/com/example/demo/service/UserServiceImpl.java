package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Override
    public User findByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public Boolean createUser(User user) {
        try {
            if (userRepository.findByEmail(user.getUserName()) != null) {
                return false;
            }
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setEnabled(true); // or false based on your requirements
            userRepository.save(user);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Boolean updateUser(User user) {
        try {
            userRepository.save(user);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public User findById(int id) {
        return userRepository.findById(id).get();
    }
}
