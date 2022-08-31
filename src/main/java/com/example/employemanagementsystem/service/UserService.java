package com.example.employemanagementsystem.service;

import com.example.employemanagementsystem.model.binding.UserLoginBindingModel;
import com.example.employemanagementsystem.model.binding.UserRegisterBindingModel;

public interface UserService {
    void seedUsers();
    void registerUser(UserRegisterBindingModel bindingModel);
    boolean isUserNameFree(String name);
    boolean passwordsChecker(UserRegisterBindingModel bindingModel);
    void loginUser(UserLoginBindingModel userLoginBindingModel);
}
