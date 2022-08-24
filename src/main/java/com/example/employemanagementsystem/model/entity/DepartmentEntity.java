package com.example.employemanagementsystem.model.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "departments")
public class DepartmentEntity extends BaseEntity {

    @Column(nullable = false)
    private String name;

    @OneToMany(cascade = CascadeType.ALL)
    private List<EmployeeEntity> employeeEntities;

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
}
