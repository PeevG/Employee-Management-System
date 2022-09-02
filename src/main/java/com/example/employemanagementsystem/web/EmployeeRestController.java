package com.example.employemanagementsystem.web;

import com.example.employemanagementsystem.model.binding.EmployeeGetAllBindingModel;
import com.example.employemanagementsystem.service.EmployeeService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employees")
public class EmployeeRestController {

    private final EmployeeService employeeService;

    public EmployeeRestController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/paginated/{pageNo}/{pageSize}")
    public Page<EmployeeGetAllBindingModel> employeePagination(@PathVariable Integer pageNo,
                                                               @PathVariable Integer pageSize,
                                                               EmployeeGetAllBindingModel bindingModel){
        //ToDo: Implementation



        return employeeService.getEmployeePageable(pageNo, pageSize);
    }
}
