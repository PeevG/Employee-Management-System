package com.example.employemanagementsystem.web;

import com.example.employemanagementsystem.model.binding.EmployeeAddBindingModel;
import com.example.employemanagementsystem.model.binding.EmployeeGetAllBindingModel;
import com.example.employemanagementsystem.model.binding.EmployeeUpdateBindingModel;
import com.example.employemanagementsystem.model.entity.EmployeeEntity;
import com.example.employemanagementsystem.model.view.DepartmentViewModel;
import com.example.employemanagementsystem.service.DepartmentService;
import com.example.employemanagementsystem.service.EmployeeService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService employeeService;
    private final DepartmentService departmentService;

    public EmployeeController(EmployeeService employeeService, DepartmentService departmentService) {
        this.employeeService = employeeService;
        this.departmentService = departmentService;
    }

    @GetMapping("/all")
    public ModelAndView showEmployees() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("get-employees");
        modelAndView.addObject("listOfEmployees", employeeService.showAllEmployees());
        modelAndView.addObject("listOfDepartments", departmentService.showAllDepartments()
                .stream().map(DepartmentViewModel::getName).collect(Collectors.toList()));
        return modelAndView;
    }

    @GetMapping("/add")
    public String addEmployee(Model model) {
        if (!model.containsAttribute("employeeAddBindingModel")) {
            model.addAttribute("employeeAddBindingModel", new EmployeeAddBindingModel());
            model.addAttribute("listOfDepartments", departmentService.showAllDepartments()
                    .stream().map(DepartmentViewModel::getName).collect(Collectors.toList()));
        }
        return "add-employee";
    }

    @PostMapping("/add")
    public String addEmployee(@Valid EmployeeAddBindingModel employeeAddBindingModel,
                              BindingResult bindingResult,
                              RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("employeeAddBindingModel", employeeAddBindingModel);
            redirectAttributes.addFlashAttribute("listOfDepartments", departmentService.showAllDepartments()
                    .stream().map(DepartmentViewModel::getName).collect(Collectors.toList()));
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.employeeAddBindingModel"
                    ,bindingResult);
            return "redirect:/employees/add";
        }
        employeeService.addEmployee(employeeAddBindingModel);
        return "redirect:/employees/all";
    }

    @GetMapping("/{id}/update")
    public String updateEmployee(@PathVariable Long id, Model model) {
        EmployeeUpdateBindingModel employeeUpdateBindingModel = employeeService.getEmployeeById(id);
        model.addAttribute("employeeUpdateBindingModel", employeeUpdateBindingModel);
        model.addAttribute("listOfDepartments", departmentService.showAllDepartments()
                .stream().map(DepartmentViewModel::getName).collect(Collectors.toList()));
        return "update-employee";
    }

    @GetMapping("/{id}/update/errors")
    public String updateEmployeeErrors(Model model) {

        model.addAttribute("listOfDepartments", departmentService
                .showAllDepartments()
                .stream()
                .map(DepartmentViewModel::getName).collect(Collectors.toList()));
        return "update-employee";
    }

    @PatchMapping("/{id}/update")
    public String updateEmployee(@PathVariable Long id,
                                 @Valid EmployeeUpdateBindingModel bindingModel,
                                 BindingResult bindingResult,
                                 RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("employeeUpdateBindingModel", bindingModel);
            redirectAttributes.addFlashAttribute("listOfDepartments", departmentService.showAllDepartments());
            redirectAttributes
                    .addFlashAttribute("org.springframework.validation.BindingResult.employeeUpdateBindingModel"
                            ,bindingResult);

            return "redirect:/employees/" + id + "/update/errors";
        }

        employeeService.updateEmployee(bindingModel);
        return "redirect:/employees/all";
    }

    @GetMapping("/{id}")
    public String deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
        return "redirect:/employees/all";
    }


}

