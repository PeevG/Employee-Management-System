package com.example.employemanagementsystem.service;
import com.example.employemanagementsystem.model.binding.ProjectAddBindingModel;
import com.example.employemanagementsystem.model.binding.ProjectUpdateBindingModel;
import com.example.employemanagementsystem.model.view.EmployeesBasicViewModel;
import com.example.employemanagementsystem.model.view.ProjectViewModel;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProjectService {
    ProjectViewModel getById(Long id);
    ProjectUpdateBindingModel getUpdateModelById(Long id);
    Page<ProjectViewModel> getProjectsPageable(Integer pageNo);
    void seedProjects();
    List<EmployeesBasicViewModel> getProjectMembers(Long id);
    void addProject(ProjectAddBindingModel projectModel);
    void updateProject(ProjectUpdateBindingModel bindingModel);

    ProjectUpdateBindingModel getProjectToUpdate(Long id);

    void deleteProject(Long id);


}
