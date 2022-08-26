package com.example.employemanagementsystem.service;

import com.example.employemanagementsystem.model.binding.EmployeeAddBindingModel;
import com.example.employemanagementsystem.model.binding.EmployeeGetAllBindingModel;
import com.example.employemanagementsystem.model.binding.EmployeeUpdateBindingModel;
import org.springframework.data.domain.Page;

import java.util.List;

public interface EmployeeService {

    List<EmployeeGetAllBindingModel> showAllEmployees();

    void addEmployee(EmployeeAddBindingModel employeeAddBindingModel);
    EmployeeUpdateBindingModel getEmployeeById(long id);
    void updateEmployee(EmployeeUpdateBindingModel employeeUpdateBindingModel);
    void deleteEmployee(Long id);
    Page<EmployeeGetAllBindingModel> findPaginated(int pageNo, int pageSize);
}
