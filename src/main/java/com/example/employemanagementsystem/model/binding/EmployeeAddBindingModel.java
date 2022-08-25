package com.example.employemanagementsystem.model.binding;

import org.hibernate.validator.constraints.Length;

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
    private Integer age;

    @Email
    private String email;

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
}
