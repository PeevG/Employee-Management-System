package com.example.employemanagementsystem.model.view;

import com.example.employemanagementsystem.model.binding.EmployeeGetAllBindingModel;

import java.util.List;

public class DepartmentViewModel {

    private Long id;
    private String name;
    private String description;

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

}
