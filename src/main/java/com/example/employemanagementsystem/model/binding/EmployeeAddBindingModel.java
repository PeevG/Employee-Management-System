package com.example.employemanagementsystem.model.binding;

import javax.validation.constraints.*;

public class EmployeeAddBindingModel {

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
}
