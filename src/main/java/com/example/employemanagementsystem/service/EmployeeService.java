package com.example.employemanagementsystem.service;

import com.example.employemanagementsystem.model.binding.EmployeeGetAllDto;

import java.util.List;

public interface EmployeeService {

    List<EmployeeGetAllDto> showAllEmployees();
}
