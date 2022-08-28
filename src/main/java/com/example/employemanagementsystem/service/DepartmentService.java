package com.example.employemanagementsystem.service;

import com.example.employemanagementsystem.model.binding.DepartmentAddBindingModel;
import com.example.employemanagementsystem.model.binding.DepartmentUpdateBindingModel;
import com.example.employemanagementsystem.model.entity.DepartmentEntity;
import com.example.employemanagementsystem.model.view.DepartmentViewModel;
import com.example.employemanagementsystem.model.view.EmployeesPerDepartmentViewModel;
import com.example.employemanagementsystem.repository.DepartmentRepository;

import java.util.List;
import java.util.Optional;

public interface DepartmentService {

    List<DepartmentViewModel> showAllDepartments();
    void addDepartment(DepartmentAddBindingModel departmentAddBindingModel);
    void updateDepartment(DepartmentUpdateBindingModel departmentUpdateBindingModel);
    DepartmentUpdateBindingModel getById(Long id);
    void deleteDepartment(Long id);
    List<EmployeesPerDepartmentViewModel> getEmployeesByDepartment(Long id);
    boolean findDepartmentByName(String name);
}
