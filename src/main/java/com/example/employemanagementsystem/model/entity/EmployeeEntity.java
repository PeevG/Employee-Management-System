package com.example.employemanagementsystem.model.entity;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import java.math.BigDecimal;
import java.time.LocalDate;
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

    @Column(name = "hire_date")
    private LocalDate hireDate;

    @Column(nullable = false)
    private BigDecimal salary;

    @ManyToOne()
    private DepartmentEntity department;

    public LocalDate getHireDate() {
        return hireDate;
    }

    public EmployeeEntity setHireDate(LocalDate hireDate) {
        this.hireDate = hireDate;
        return this;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public EmployeeEntity setSalary(BigDecimal salary) {
        this.salary = salary;
        return this;
    }

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
}
