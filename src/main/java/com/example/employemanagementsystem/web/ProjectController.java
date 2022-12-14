package com.example.employemanagementsystem.web;

import com.example.employemanagementsystem.model.binding.EmployeeGetAllBindingModel;
import com.example.employemanagementsystem.model.binding.ProjectAddBindingModel;
import com.example.employemanagementsystem.model.binding.ProjectUpdateBindingModel;
import com.example.employemanagementsystem.model.entity.EmployeeEntity;
import com.example.employemanagementsystem.model.view.EmployeeDetailsViewModel;
import com.example.employemanagementsystem.model.view.EmployeesBasicViewModel;
import com.example.employemanagementsystem.model.view.ProjectViewModel;
import com.example.employemanagementsystem.service.EmployeeService;
import com.example.employemanagementsystem.service.ProjectService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/projects")
public class ProjectController {

    private final ProjectService projectService;
    private final EmployeeService employeeService;

    public ProjectController(ProjectService projectService, EmployeeService employeeService) {
        this.projectService = projectService;
        this.employeeService = employeeService;
    }

    @GetMapping("/pageable")
    public String getProjectsPageOne(Model model) {
        return getProjectsPageable(1, model);
    }

    @GetMapping("/{id}/details")
    public String showProjectDescription(@PathVariable Long id,
                                         Model model) {
        model.addAttribute("project", projectService.getById(id));
        model.addAttribute("projectDescription", projectService.getById(id).getDescription());
        model.addAttribute("projectMembersNames", projectService.getProjectMembers(id)
                .stream()
                .map(employeesBasicViewModel -> String.join(" ", employeesBasicViewModel.getFirstName(),
                        employeesBasicViewModel.getLastName())).collect(Collectors.toList()));

        model.addAttribute("projectName", projectService.getById(id).getName());

        return "project-details";
    }

    @GetMapping("/pageable/{pageNo}")
    public String getProjectsPageable(@PathVariable Integer pageNo,
                                      Model model) {

        Page<ProjectViewModel> projectsPageable = projectService.getProjectsPageable(pageNo);
        List<ProjectViewModel> projectsList = projectsPageable.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", projectsPageable.getTotalPages());
        model.addAttribute("ListOfProjects", projectsList);
        model.addAttribute("totalProjects", projectsPageable.getTotalElements());

        return "get-projects";
    }

    @GetMapping("/addProject")
    public String addProject(Model model) {
        if (!model.containsAttribute("addProjectModel"))
            model.addAttribute("addProjectModel", new ProjectAddBindingModel());
        return "project-add";
    }

    @PostMapping("/addProject")
    public String addProject(@Valid ProjectAddBindingModel projectModel,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("addProjectModel", projectModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.addProjectModel"
                    , bindingResult);
            return "project-add";
        }

        projectService.addProject(projectModel);
        return "redirect:/projects/pageable";
    }

    @GetMapping("/{id}/update")
    public String updateProject(@PathVariable Long id,
                                Model model) {
        ProjectUpdateBindingModel projectModel = projectService.getUpdateModelById(id);
        model.addAttribute("ProjectUpdateBindingModel", projectModel);

        return "project-update";
    }

    //Todo: - Visualise error messages in update project Form.
    @GetMapping("/{id}/update/errors")
    public String updateProjectErrors(@PathVariable Long id, Model model) {
        ProjectUpdateBindingModel projectToUpdate = projectService.getProjectToUpdate(id);
        model.addAttribute(projectToUpdate);

        return "project-update";
    }

    @PatchMapping("/{id}/update")
    public String updateProject(@PathVariable Long id,
                                @Valid ProjectUpdateBindingModel bindingModel,
                                BindingResult bindingResult,
                                RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("ProjectUpdateBindingModel", bindingModel);
            redirectAttributes
                    .addFlashAttribute("org.springframework.validation.BindingResult.ProjectUpdateBindingModel"
                            , bindingResult);

            return "redirect:/projects/" + id + "/update/errors";
        }
        projectService.updateProject(bindingModel);
        return "redirect:/projects/pageable";
    }

    @GetMapping("/{id}/delete")
    public String deleteProject(@PathVariable Long id) {
        projectService.deleteProject(id);
        return "redirect:/projects/pageable";
    }

    @GetMapping("/{id}/addMember")
    public String addMember(@PathVariable long id, Model model){
        List<EmployeeGetAllBindingModel> employeesWhoNotInTheCurrentProject = employeeService.getEmployeesWhoNotInTheCurrentProject(id);
        model.addAttribute("ListOfEmployees",employeesWhoNotInTheCurrentProject);
        return "add-member-to-project";
    }

    @PostMapping("/{projectId}/addMember/{employeeId}")
    public String addMember(@PathVariable long projectId, long employeeId, Model model){

        Model project = model.addAttribute(projectService.getById(projectId));
        model.addAttribute("p roject", project);
        employeeService.addEmployeeToProject(projectId, employeeId);
        return "redirect:/projects/pageable";
    }
}
