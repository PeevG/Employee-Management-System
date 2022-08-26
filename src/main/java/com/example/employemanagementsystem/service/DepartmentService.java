package com.example.employemanagementsystem.service;

import com.example.employemanagementsystem.model.binding.DepartmentAddBindingModel;
import com.example.employemanagementsystem.model.view.DepartmentViewModel;
import com.example.employemanagementsystem.repository.DepartmentRepository;

import java.util.List;

public interface DepartmentService {

    List<DepartmentViewModel> showAllDepartments();

    void addDepartment(DepartmentAddBindingModel departmentAddBindingModel);
}
