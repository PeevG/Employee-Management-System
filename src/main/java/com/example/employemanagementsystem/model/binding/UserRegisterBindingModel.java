package com.example.employemanagementsystem.model.binding;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

public class UserRegisterBindingModel {

    @NotBlank(message = "Username is required and should be between 3 - 18 symbols.")
    @Min(3)@Max(18)
    private String userName;

    @NotBlank(message = "First name is required and should be between 3 - 18 symbols.")
    @Min(3)@Max(18)
    private String firstName;

    @NotBlank(message = "Last name is required and should be between 3 - 18 symbols.")
    @Min(3)@Max(18)
    private String lastName;

    @NotBlank(message = "Password can not be empty and less than 6 symbols.")
    @Min(6)
    private String password;

    @NotBlank(message = "Confirm password can not be empty and less than 6 symbols.")
    @Min(6)
    private String confirmPassword;

    @Email
    private String email;

    public String getUserName() {
        return userName;
    }

    public UserRegisterBindingModel setUserName(String userName) {
        this.userName = userName;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public UserRegisterBindingModel setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public UserRegisterBindingModel setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserRegisterBindingModel setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public UserRegisterBindingModel setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserRegisterBindingModel setEmail(String email) {
        this.email = email;
        return this;
    }
}
