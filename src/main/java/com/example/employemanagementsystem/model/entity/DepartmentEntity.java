package com.example.employemanagementsystem.model.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "departments")
public class DepartmentEntity extends BaseEntity {

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "department",cascade = CascadeType.ALL)
    private List<EmployeeEntity> employeeEntities = new ArrayList<>();

    @Column(columnDefinition = "LONGTEXT")
    private String description;

    public String getName() {
        return name;
    }

    public DepartmentEntity setName(String name) {
        this.name = name;
        return this;
    }

    public List<EmployeeEntity> getEmployees() {
        return employeeEntities;
    }

    public DepartmentEntity setEmployees(List<EmployeeEntity> employeeEntities) {
        this.employeeEntities = employeeEntities;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public DepartmentEntity setDescription(String description) {
        this.description = description;
        return this;
    }

    public List<EmployeeEntity> getEmployeeEntities() {
        return employeeEntities;
    }

    public DepartmentEntity setEmployeeEntities(List<EmployeeEntity> employeeEntities) {
        this.employeeEntities = employeeEntities;
        return this;
    }
}
