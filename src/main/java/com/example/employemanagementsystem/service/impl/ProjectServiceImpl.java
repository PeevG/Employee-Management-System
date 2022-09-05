package com.example.employemanagementsystem.service.impl;

import com.example.employemanagementsystem.model.entity.ProjectEntity;
import com.example.employemanagementsystem.model.view.ProjectViewModel;
import com.example.employemanagementsystem.repository.ProjectRepository;
import com.example.employemanagementsystem.service.ProjectService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {

    private final ModelMapper modelMapper;
    private final ProjectRepository projectRepository;

    public ProjectServiceImpl(ModelMapper modelMapper, ProjectRepository projectRepository) {
        this.modelMapper = modelMapper;
        this.projectRepository = projectRepository;
    }

    @Override
    public Page<ProjectViewModel> getProjectsPageable(Integer pageNo) {
        Pageable pageable = PageRequest.of(pageNo - 1, 8);

        Page<ProjectEntity> projectsPageable = projectRepository
                .findAll(pageable);

        return projectsPageable.map(this::mapProjectToDTO);

    }

    @Override
    public void seedProjects() {
        if(projectRepository.count() < 1) {
            ProjectEntity projectOne = new ProjectEntity();
            projectOne.setName("ERP System")
                    .setProjectNumber("ABC123")
                    .setStartDate(LocalDate.now())
                    .setDuration("4 days")
                    .setDescription("Small ERP system for our new client.")
                    .setProjectMembers(new ArrayList<>());

            ProjectEntity projectTwo = new ProjectEntity();
            projectTwo.setName("Mobile application")
                    .setProjectNumber("MOB543")
                    .setStartDate(LocalDate.now())
                    .setDuration("7 days")
                    .setDescription("Mobile application bla bla bla..")
                    .setProjectMembers(new ArrayList<>());

            ProjectEntity projectThree = new ProjectEntity();
            projectThree.setName("Web application")
                    .setProjectNumber("WEB323")
                    .setStartDate(LocalDate.now())
                    .setDuration("14 days")
                    .setDescription("Bank software. Bla bla bla ..")
                    .setProjectMembers(new ArrayList<>());

            projectRepository.saveAll(List.of(projectOne, projectTwo, projectThree));
        }
    }

    public ProjectViewModel mapProjectToDTO(ProjectEntity projectEntity) {
        ProjectViewModel projectModel = modelMapper.map(projectEntity, ProjectViewModel.class);
        return projectModel;
    }
}
