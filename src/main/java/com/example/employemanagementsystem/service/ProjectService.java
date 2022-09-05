package com.example.employemanagementsystem.service;

import com.example.employemanagementsystem.model.view.ProjectViewModel;
import org.springframework.data.domain.Page;

public interface ProjectService {

    Page<ProjectViewModel> getProjectsPageable(Integer pageNo);
    void seedProjects();
}
