package com.example.employemanagementsystem.model.binding;

import javax.validation.constraints.*;
import java.math.BigDecimal;

public class EmployeeAddBindingModel {

    private Long id;

    @NotBlank()
    @Size(min = 3, max = 25)
    private String firstName;

    @NotBlank()
    @Size(min = 3, max = 25)
    private String lastName;

    @NotNull()
    @Min(18)
    @Max(70)
    private Integer age;

    @Email
    private String email;

    @DecimalMin("750")
    @NotNull
    private BigDecimal salary;

    @NotBlank()
    @Size(min = 3, max = 25)
    private String department;

    public String getFirstName() {
        return firstName;
    }

    public EmployeeAddBindingModel setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public EmployeeAddBindingModel setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public Integer getAge() {
        return age;
    }

    public EmployeeAddBindingModel setAge(Integer age) {
        this.age = age;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public EmployeeAddBindingModel setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getDepartment() {
        return department;
    }

    public EmployeeAddBindingModel setDepartment(String department) {
        this.department = department;
        return this;
    }

    public Long getId() {
        return id;
    }

    public EmployeeAddBindingModel setId(Long id) {
        this.id = id;
        return this;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public EmployeeAddBindingModel setSalary(BigDecimal salary) {
        this.salary = salary;
        return this;
    }
}
