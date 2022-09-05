package com.example.employemanagementsystem.model.view;

public class EmployeesPerDepartmentViewModel {
    private Long id;
    private String firstName;
    private String lastName;

    public Long getId() {
        return id;
    }

    public EmployeesPerDepartmentViewModel setId(Long id) {
        this.id = id;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public EmployeesPerDepartmentViewModel setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public EmployeesPerDepartmentViewModel setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }
}
