package com.example.employemanagementsystem.service;

import com.example.employemanagementsystem.model.binding.EmployeeAddBindingModel;
import com.example.employemanagementsystem.model.binding.EmployeeGetAllBindingModel;

import java.util.List;

public interface EmployeeService {

    List<EmployeeGetAllBindingModel> showAllEmployees();
    void addEmployee(EmployeeAddBindingModel employeeAddBindingModel);
}
