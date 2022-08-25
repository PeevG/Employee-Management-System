package com.example.employemanagementsystem.model.binding;

import javax.validation.constraints.*;

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
    private String email;

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
}
