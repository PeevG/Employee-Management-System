package com.example.employemanagementsystem.web;

import com.example.employemanagementsystem.service.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/all")
    public ModelAndView showEmployees(){
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName("get-employees");
        modelAndView.addObject("listOfEmployees", employeeService.showAllEmployees());

        return modelAndView;
    }
}
