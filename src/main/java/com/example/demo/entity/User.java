package com.example.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @NotBlank(message = "Username cannot be blank*")
    @Size(max = 50, message = "Username cannot exceed 50 characters*")
    @Column(name = "user_name")
    private String userName;

    @Size(min = 6, message = "Password must be at least 6 characters long*")
    private String password;
    private boolean enabled;

    @NotBlank(message = "Full name cannot be blank*")
    @Size(max = 100, message = "Full name cannot exceed 100 characters*")
    @Column(name = "full_Name")
    private String fullName;

    @NotBlank(message = "Address cannot be blank*")
    @Size(max = 255, message = "Address cannot exceed 255 characters*")
    private String address;

    @Email(message = "Email should be valid*")
    @Size(max = 100, message = "Email cannot exceed 100 characters*")
    private String email;

    @Pattern(regexp = "^\\+?[0-9]{10,15}$", message = "Phone number should be valid*")
    @Size(max = 15, message = "Phone number cannot exceed 15 characters*")
    private String phone;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private Set<UserRole> userRoles = new HashSet<>();

    public User() {
    }

    public User(Set<UserRole> userRoles, String phone, String email, String address, boolean enabled, String fullName, String password, String userName, int id) {
        this.userRoles = userRoles;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.enabled = enabled;
        this.fullName = fullName;
        this.password = password;
        this.userName = userName;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Set<UserRole> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(Set<UserRole> userRoles) {
        this.userRoles = userRoles;
    }
}
