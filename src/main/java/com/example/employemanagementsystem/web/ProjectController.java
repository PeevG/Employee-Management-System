package com.example.employemanagementsystem.web;

import com.example.employemanagementsystem.model.view.ProjectViewModel;
import com.example.employemanagementsystem.service.ProjectService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/projects")
public class ProjectController {

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
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
}
