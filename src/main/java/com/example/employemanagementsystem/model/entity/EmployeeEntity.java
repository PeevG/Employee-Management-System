package com.example.employemanagementsystem.model.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "employees")
public class EmployeeEntity extends BaseEntity {

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(nullable = false)
    private Integer age;

    @Column
    private String email;

    @ManyToOne()
    private DepartmentEntity department;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<ProjectEntity> projects;

    public String getFirstName() {
        return firstName;
    }

    public EmployeeEntity setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public EmployeeEntity setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public Integer getAge() {
        return age;
    }

    public EmployeeEntity setAge(Integer age) {
        this.age = age;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public EmployeeEntity setEmail(String email) {
        this.email = email;
        return this;
    }

    public DepartmentEntity getDepartment() {
        return department;
    }

    public EmployeeEntity setDepartment(DepartmentEntity department) {
        this.department = department;
        return this;
    }

    public List<ProjectEntity> getProjects() {
        return projects;
    }

    public EmployeeEntity setProjects(List<ProjectEntity> projects) {
        this.projects = projects;
        return this;
    }
}
