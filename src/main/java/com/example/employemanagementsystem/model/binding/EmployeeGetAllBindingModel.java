package com.example.employemanagementsystem.model.binding;

public class EmployeeGetAllBindingModel {
    private String firstName;
    private String lastName;
    private String email;

    public String getFirstName() {
        return firstName;
    }

    public EmployeeGetAllBindingModel setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public EmployeeGetAllBindingModel setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public EmployeeGetAllBindingModel setEmail(String email) {
        this.email = email;
        return this;
    }
}
