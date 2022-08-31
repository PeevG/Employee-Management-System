package com.example.employemanagementsystem.model.binding;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class UserLoginBindingModel {

    @NotBlank
    private String email;

    @NotBlank
    private String username;

    @NotBlank
    @Email
    private String password;

    public String getUsername() {
        return username;
    }

    public UserLoginBindingModel setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserLoginBindingModel setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserLoginBindingModel setPassword(String password) {
        this.password = password;
        return this;
    }
}
