package com.example.employemanagementsystem.model.binding;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class DepartmentUpdateBindingModel {

    private Long id;
    @NotBlank()
    @Size(min = 3, max = 25)
    private String name;

    @NotBlank
    private String description;

    public Long getId() {
        return id;
    }

    public DepartmentUpdateBindingModel setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public DepartmentUpdateBindingModel setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public DepartmentUpdateBindingModel setDescription(String description) {
        this.description = description;
        return this;
    }
}
