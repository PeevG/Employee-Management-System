package com.example.employemanagementsystem.model.binding;

public class EmployeeGetAllBindingModel {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String department;

    public Long getId() {
        return id;
    }

    public EmployeeGetAllBindingModel setId(Long id) {
        this.id = id;
        return this;
    }

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

    public String getDepartment() {
        return department;
    }

    public EmployeeGetAllBindingModel setDepartment(String department) {
        this.department = department;
        return this;
    }
}
