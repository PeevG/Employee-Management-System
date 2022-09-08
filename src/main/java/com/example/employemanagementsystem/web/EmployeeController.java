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
import java.util.ArrayList;
import java.util.List;
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
    public String showEmployees() {
        return "employees/pageable";
    }

    @GetMapping("/{id}/details")
    public String showEmployeeDetailsView(@PathVariable Long id, Model model) {
        model.addAttribute("employeeFName", employeeService.getEmployeeById(id).getFirstName());
        model.addAttribute("employeeModel", employeeService.getEmployeeDetailsById(id));
        model.addAttribute("departmentName", employeeService.getEmployeeDetailsById(id).getDepartment().getName());

        return "employee-details";
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
                    , bindingResult);
            return "redirect:/employees/add";
        }
        employeeService.addEmployee(employeeAddBindingModel);
        return "redirect:/employees/pageable";
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
                            , bindingResult);

            return "redirect:/employees/" + id + "/update/errors";
        }

        employeeService.updateEmployee(bindingModel);
        return "redirect:/employees/pageable";
    }

    @GetMapping("/{id}")
    public String deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
        return "redirect:/employees/pageable";
    }

    @GetMapping("/pageable")
    public String employeePagination(Model model) {
        return employeePaginationPage(1, model);
    }

    @GetMapping("/pageable/{pageNo}")
    public String employeePaginationPage(@PathVariable Integer pageNo,
                                         Model model) {

        Page<EmployeeGetAllBindingModel> bindingModelPage = employeeService.getEmployeePageable(pageNo, null, null);
        List<EmployeeGetAllBindingModel> listOfEmployees = bindingModelPage.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", bindingModelPage.getTotalPages());
        model.addAttribute("totalEmployees", bindingModelPage.getTotalElements());
        model.addAttribute("listOfEmployees", listOfEmployees);

        return "get-employees";
    }

    @GetMapping("/pageable/{pageNo}/{pageSize}")
    public String employeePaginationPageSize(@PathVariable Integer pageNo,
                                             @PathVariable Integer pageSize,
                                             Model model) {

        Page<EmployeeGetAllBindingModel> bindingModelPage = employeeService.getEmployeePageable(pageNo, pageSize, null);
        List<EmployeeGetAllBindingModel> listOfEmployees = bindingModelPage.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", bindingModelPage.getTotalPages());
        model.addAttribute("totalEmployees", bindingModelPage.getTotalElements());
        model.addAttribute("listOfEmployees", listOfEmployees);

        return "get-employees";
    }

    @GetMapping("/pageable/{pageNo}/{pageSize}/{sortProperty}")
    public Page<EmployeeGetAllBindingModel> employeePaginationPageSizeSort(@PathVariable Integer pageNo,
                                                                           @PathVariable Integer pageSize,
                                                                           @PathVariable String sortProperty,
                                                                           Model model) {
        //ToDo: Implementation
        return employeeService.getEmployeePageable(pageNo, pageSize, sortProperty);
    }

}

