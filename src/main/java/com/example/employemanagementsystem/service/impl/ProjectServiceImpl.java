package com.example.employemanagementsystem.service.impl;

import com.example.employemanagementsystem.exception.ObjectNotFoundException;
import com.example.employemanagementsystem.model.binding.ProjectAddBindingModel;
import com.example.employemanagementsystem.model.binding.ProjectUpdateBindingModel;
import com.example.employemanagementsystem.model.entity.DepartmentEntity;
import com.example.employemanagementsystem.model.entity.EmployeeEntity;
import com.example.employemanagementsystem.model.entity.ProjectEntity;
import com.example.employemanagementsystem.model.view.EmployeesBasicViewModel;
import com.example.employemanagementsystem.model.view.ProjectViewModel;
import com.example.employemanagementsystem.repository.EmployeeRepository;
import com.example.employemanagementsystem.repository.ProjectRepository;
import com.example.employemanagementsystem.service.ProjectService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectServiceImpl implements ProjectService {

    private final ModelMapper modelMapper;
    private final ProjectRepository projectRepository;
    private final EmployeeRepository employeeRepository;

    public ProjectServiceImpl(ModelMapper modelMapper, ProjectRepository projectRepository, EmployeeRepository employeeRepository) {
        this.modelMapper = modelMapper;
        this.projectRepository = projectRepository;
        this.employeeRepository = employeeRepository;
    }

    @Override
    public ProjectViewModel getById(Long id) {
        ProjectEntity project =
                projectRepository.findById(id)
                        .orElseThrow(() -> new ObjectNotFoundException("Project with id " + id + " is not found"));
        return modelMapper.map(project, ProjectViewModel.class);
    }

    @Override
    public ProjectUpdateBindingModel getUpdateModelById(Long id) {
        ProjectEntity project = projectRepository
                .findById(id).orElseThrow(() -> new ObjectNotFoundException("Project with id " + id + " not exist."));
        ProjectUpdateBindingModel projectModel = modelMapper.map(project, ProjectUpdateBindingModel.class);
        return projectModel;
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
        if (projectRepository.count() < 1) {
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
                    .setDescription("Mobile application bla bla bla")
                    .setProjectMembers(List.of(employeeRepository.getById(1L), employeeRepository.getById(2L),
                            employeeRepository.getById(4L), employeeRepository.getById(3L), employeeRepository.getById(5L)));

            ProjectEntity projectThree = new ProjectEntity();
            projectThree.setName("Web application")
                    .setProjectNumber("WEB323")
                    .setStartDate(LocalDate.now())
                    .setDuration("14 days")
                    .setDescription("Bank software. Bla bla bla ..")
                    .setProjectMembers(List.of(employeeRepository.getById(3L), employeeRepository.getById(2L),
                            employeeRepository.getById(4L)));


            ProjectEntity projectFour = new ProjectEntity();
            projectFour.setName("Web application2")
                    .setProjectNumber("WEB323")
                    .setStartDate(LocalDate.now())
                    .setDuration("15 days")
                    .setDescription("Test Test software. Bla bla bla ..")
                    .setProjectMembers(List.of(employeeRepository.getById(1L), employeeRepository.getById(2L)));

            projectRepository.saveAll(List.of(projectOne, projectTwo, projectThree, projectFour));
        }
    }

    @Override
    public List<EmployeesBasicViewModel> getProjectMembers(Long id) {
        ProjectViewModel projectModel = getById(id);
        List<EmployeeEntity> projectMembers = projectModel.getProjectMembers();

        return projectMembers.stream().map(employee -> modelMapper.map(employee, EmployeesBasicViewModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public void addProject(ProjectAddBindingModel projectModel) {
        ProjectEntity project = modelMapper.map(projectModel, ProjectEntity.class);
        project.setStartDate(LocalDate.now());
        project.setProjectNumber(generateRandomProjectNumber());
        project.setProjectMembers(new ArrayList<>());

        projectRepository.save(project);
    }

    @Override
    public void updateProject(ProjectUpdateBindingModel bindingModel) {
        ProjectEntity project = projectRepository.findById(bindingModel.getId())
                .orElseThrow(() -> new ObjectNotFoundException("Project with id " + bindingModel.getId()
                + " is not found."));

        project.setName(bindingModel.getName())
                .setDuration(bindingModel.getDuration())
                .setDescription(bindingModel.getDescription());

        projectRepository.save(project);
    }

    @Override
    public ProjectUpdateBindingModel getProjectToUpdate(Long id) {
        ProjectEntity project = projectRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Project with id " + id + " is not found"));
        return modelMapper.map(project, ProjectUpdateBindingModel.class);
    }

    private String generateRandomProjectNumber() {
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder sb = new StringBuilder(6);

        for (int i = 0; i < 6; i++) {
            int index
                    = (int) (AlphaNumericString.length()
                    * Math.random());
            sb.append(AlphaNumericString
                    .charAt(index));
        }
        return sb.toString();
    }

    public ProjectViewModel mapProjectToDTO(ProjectEntity projectEntity) {
        return modelMapper.map(projectEntity, ProjectViewModel.class);
    }
}
