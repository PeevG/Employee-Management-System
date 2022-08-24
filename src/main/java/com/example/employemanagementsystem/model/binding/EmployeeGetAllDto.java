package com.example.employemanagementsystem.model.binding;

public class EmployeeGetAllDto {
    private String firstName;
    private String lastName;
    private String email;

    public String getFirstName() {
        return firstName;
    }

    public EmployeeGetAllDto setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public EmployeeGetAllDto setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public EmployeeGetAllDto setEmail(String email) {
        this.email = email;
        return this;
    }
}
