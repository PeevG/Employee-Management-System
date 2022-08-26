package com.example.employemanagementsystem.model.binding;
import java.util.ArrayList;
import java.util.List;

public class DepartmentAddBindingModel {

    private Long id;
    private String name;
    private List<EmployeeGetAllBindingModel> employees;
    private String description;

    public Long getId() {
        return id;
    }

    public DepartmentAddBindingModel setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public DepartmentAddBindingModel setName(String name) {
        this.name = name;
        return this;
    }

    public List<EmployeeGetAllBindingModel> getEmployees() {
        return employees;
    }

    public DepartmentAddBindingModel setEmployees(List<EmployeeGetAllBindingModel> employees) {
        this.employees = employees;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public DepartmentAddBindingModel setDescription(String description) {
        this.description = description;
        return this;
    }
}
