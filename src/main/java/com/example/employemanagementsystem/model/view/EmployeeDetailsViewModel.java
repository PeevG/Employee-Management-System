package com.example.employemanagementsystem.model.view;

import com.example.employemanagementsystem.model.entity.DepartmentEntity;

import java.math.BigDecimal;
import java.time.LocalDate;

public class EmployeeDetailsViewModel {

    private Long id;
    private String firstName;
    private String lastName;
    private Integer age;
    private String email;
    private LocalDate hireDate;
    private BigDecimal salary;
    private DepartmentEntity department;

    public Long getId() {
        return id;
    }

    public EmployeeDetailsViewModel setId(Long id) {
        this.id = id;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public EmployeeDetailsViewModel setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public EmployeeDetailsViewModel setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public Integer getAge() {
        return age;
    }

    public EmployeeDetailsViewModel setAge(Integer age) {
        this.age = age;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public EmployeeDetailsViewModel setEmail(String email) {
        this.email = email;
        return this;
    }

    public LocalDate getHireDate() {
        return hireDate;
    }

    public EmployeeDetailsViewModel setHireDate(LocalDate hireDate) {
        this.hireDate = hireDate;
        return this;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public EmployeeDetailsViewModel setSalary(BigDecimal salary) {
        this.salary = salary;
        return this;
    }

    public DepartmentEntity getDepartment() {
        return department;
    }

    public EmployeeDetailsViewModel setDepartment(DepartmentEntity department) {
        this.department = department;
        return this;
    }
}
