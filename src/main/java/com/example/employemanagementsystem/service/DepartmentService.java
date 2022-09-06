package com.example.employemanagementsystem.service;

import com.example.employemanagementsystem.model.binding.DepartmentAddBindingModel;
import com.example.employemanagementsystem.model.binding.DepartmentUpdateBindingModel;
import com.example.employemanagementsystem.model.view.DepartmentViewModel;
import com.example.employemanagementsystem.model.view.EmployeesBasicViewModel;

import java.util.List;

public interface DepartmentService {

    void seedDepartments();
    List<DepartmentViewModel> showAllDepartments();
    void addDepartment(DepartmentAddBindingModel departmentAddBindingModel);
    void updateDepartment(DepartmentUpdateBindingModel departmentUpdateBindingModel);
    DepartmentUpdateBindingModel getById(Long id);
    void deleteDepartment(Long id);
    List<EmployeesBasicViewModel> getEmployeesByDepartment(Long id);
    boolean findDepartmentByName(String name);
}
