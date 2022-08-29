package com.example.employemanagementsystem.service;

import com.example.employemanagementsystem.model.binding.UserRegisterBindingModel;

public interface UserService {
    void registerUser(UserRegisterBindingModel bindingModel);
    boolean isUserNameFree(String name);
    boolean passwordsChecker(UserRegisterBindingModel bindingModel);
}
