package com.example.employemanagementsystem.model.view;

public class EmployeeUpdateView {
    private Long id;
    private String firstName;
    private String lastName;
    private Integer age;
    private String email;

    public Long getId() {
        return id;
    }

    public EmployeeUpdateView setId(Long id) {
        this.id = id;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public EmployeeUpdateView setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public EmployeeUpdateView setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public Integer getAge() {
        return age;
    }

    public EmployeeUpdateView setAge(Integer age) {
        this.age = age;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public EmployeeUpdateView setEmail(String email) {
        this.email = email;
        return this;
    }
}