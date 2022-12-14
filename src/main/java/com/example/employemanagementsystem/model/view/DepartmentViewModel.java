package com.example.employemanagementsystem.model.view;

import java.util.List;

public class DepartmentViewModel {

    private Long id;
    private String name;
    private String description;
    private List<EmployeesBasicViewModel> employees;

    public Long getId() {
        return id;
    }
    public DepartmentViewModel setId(Long id) {
        this.id = id;
        return this;
    }
    public String getName() {
        return name;
    }
    public DepartmentViewModel setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public DepartmentViewModel setDescription(String description) {
        this.description = description;
        return this;
    }

    public List<EmployeesBasicViewModel> getEmployees() {
        return employees;
    }

    public DepartmentViewModel setEmployees(List<EmployeesBasicViewModel> employees) {
        this.employees = employees;
        return this;
    }
}
