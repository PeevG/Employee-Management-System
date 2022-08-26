package com.example.employemanagementsystem.web;

import com.example.employemanagementsystem.service.DepartmentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/departments")
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("/all")
    public String showAllDepartments(Model model) {
        model.addAttribute("listOfDepartments", departmentService.showAllDepartments());
        return "get-departments";
    }


}
