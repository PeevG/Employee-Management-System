package com.example.employemanagementsystem.model.binding;

import javax.validation.constraints.*;
import java.math.BigDecimal;

public class EmployeeUpdateBindingModel {

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
    @NotBlank
    private String email;

    @NotBlank
    private String department;

    @DecimalMin("750")
    @NotNull
    private BigDecimal salary;

    public Long getId() {
        return id;
    }

    public EmployeeUpdateBindingModel setId(Long id) {
        this.id = id;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public EmployeeUpdateBindingModel setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public EmployeeUpdateBindingModel setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public Integer getAge() {
        return age;
    }

    public EmployeeUpdateBindingModel setAge(Integer age) {
        this.age = age;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public EmployeeUpdateBindingModel setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getDepartment() {
        return department;
    }

    public EmployeeUpdateBindingModel setDepartment(String department) {
        this.department = department;
        return this;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public EmployeeUpdateBindingModel setSalary(BigDecimal salary) {
        this.salary = salary;
        return this;
    }
}
