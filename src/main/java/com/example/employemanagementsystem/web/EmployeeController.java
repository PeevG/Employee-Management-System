package com.example.employemanagementsystem.web;

import com.example.employemanagementsystem.model.binding.EmployeeAddBindingModel;
import com.example.employemanagementsystem.model.binding.EmployeeUpdateBindingModel;
import com.example.employemanagementsystem.model.view.EmployeeUpdateView;
import com.example.employemanagementsystem.service.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/all")
    public ModelAndView showEmployees() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("get-employees");
        modelAndView.addObject("listOfEmployees", employeeService.showAllEmployees());
        return modelAndView;
    }

    @GetMapping("/add")
    public String addEmployee(Model model) {

        if (!model.containsAttribute("employeeAddBindingModel")) {
            model.addAttribute("employeeAddBindingModel", new EmployeeAddBindingModel());
        }
        return "add-employee";
    }

    @PostMapping("/add")
    public String addEmployee(@Valid EmployeeAddBindingModel employeeAddBindingModel,
                              BindingResult bindingResult,
                              RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("employeeModel", employeeAddBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.employeeModel"
                    , bindingResult);

            return "redirect:/employees/add";
        }
        employeeService.addEmployee(employeeAddBindingModel);

        return "redirect:/employees/all";
    }

    @GetMapping("/{id}/update")
    public String updateEmployee(@PathVariable Long id, Model model) {
        EmployeeUpdateBindingModel employeeUpdateBindingModel = employeeService.getEmployeeById(id);
        model.addAttribute("employeeUpdateBindingModel", employeeUpdateBindingModel);
        return "update-employee";
    }

    @PatchMapping("/{id}/update")
    public String updateEmployee(@PathVariable Long id,
                                 @Valid EmployeeUpdateBindingModel employeeUpdateBindingModel,
                                 BindingResult bindingResult,
                                 RedirectAttributes redirectAttributes){

        if(bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("employeeUpdateBindingModel", employeeUpdateBindingModel);
            redirectAttributes
                    .addFlashAttribute("org.springframework.validation.BindingResult.employeeUpdateBindingModel"
                    ,bindingResult);

            return "redirect:/employees/" + id + "/update";
        }

        employeeService.updateEmployee(employeeUpdateBindingModel);

        return "redirect:/employees/all";
    }

}
