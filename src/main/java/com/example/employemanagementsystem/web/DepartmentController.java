package com.example.employemanagementsystem.web;

import com.example.employemanagementsystem.model.binding.DepartmentAddBindingModel;
import com.example.employemanagementsystem.model.binding.DepartmentUpdateBindingModel;
import com.example.employemanagementsystem.model.entity.DepartmentEntity;
import com.example.employemanagementsystem.repository.DepartmentRepository;
import com.example.employemanagementsystem.service.DepartmentService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/departments")
public class DepartmentController {

    private final DepartmentService departmentService;
    private final ModelMapper modelMapper;

    public DepartmentController(DepartmentService departmentService, ModelMapper modelMapper) {
        this.departmentService = departmentService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/all")
    public String showAllDepartments(Model model) {
        model.addAttribute("listOfDepartments", departmentService.showAllDepartments());
        return "get-departments";
    }

    @GetMapping("/{id}/details")
    public String showEmployeesByDepartment(@PathVariable Long id, Model model) {
        model.addAttribute("listOfDepartments", departmentService.showAllDepartments());
        model.addAttribute("employeesPerDep", departmentService.getEmployeesByDepartment(id));
        model.addAttribute("pageTitle", departmentService.getById(id).getName());
        return "employees-per-department";
    }

    @GetMapping("/add")
    public String addDepartment(Model model) {
        if (!model.containsAttribute("departmentAddBindingModel")) {
            model.addAttribute("departmentAddBindingModel", new DepartmentAddBindingModel());
        }
        return "add-department";
    }

    @PostMapping("/add")
    public String addDepartment(@Valid DepartmentAddBindingModel bindingModel,
                                BindingResult bindingResult,
                                RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors() || departmentService.findDepartmentByName(bindingModel.getName())) {
            redirectAttributes.addFlashAttribute("departmentAddBindingModel", bindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.departmentAddBindingModel"
                    ,bindingResult);
            return "redirect:/departments/add";
        }

        departmentService.addDepartment(bindingModel);
        return "redirect:/departments/all";
    }

    @GetMapping("/{id}/update")
    public String updateDepartment(@PathVariable Long id, Model model) {
        DepartmentUpdateBindingModel bindingModel = departmentService.getById(id);
        model.addAttribute("bindingModel", bindingModel);
        return "update-department";
    }

    @GetMapping("/{id}/update/errors")
    public String updateDepartmentErrors() {
        return "update-department";
    }

    @PatchMapping("/{id}/update")
    public String updateDepartment(@PathVariable Long id,
                                   @Valid DepartmentUpdateBindingModel bindingModel,
                                   BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("bindingModel", bindingModel);
            redirectAttributes
                    .addFlashAttribute("org.springframework.validation.BindingResult.bindingModel"
                            , bindingResult);
            return "redirect:/departments/" + id + "/update/errors";
        }
        departmentService.updateDepartment(bindingModel);
        return "redirect:/departments/all";
    }

    @GetMapping("/{id}/delete")
    public String deleteDepartment(@PathVariable Long id) {
        departmentService.deleteDepartment(id);
        return "redirect:/departments/all";
    }
}
