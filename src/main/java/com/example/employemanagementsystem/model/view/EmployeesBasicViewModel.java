package com.example.employemanagementsystem.model.view;

public class EmployeesBasicViewModel {
    private Long id;
    private String firstName;
    private String lastName;

    public Long getId() {
        return id;
    }

    public EmployeesBasicViewModel setId(Long id) {
        this.id = id;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public EmployeesBasicViewModel setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public EmployeesBasicViewModel setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }
}
