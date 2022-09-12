package com.example.employemanagementsystem.model.view;

import com.example.employemanagementsystem.model.entity.EmployeeEntity;

import java.time.LocalDate;
import java.util.List;

public class ProjectViewModel {

    private Long id;
    private String projectNumber;
    private String name;
    private LocalDate startDate;
    private String duration;
    private String description;
    private List<EmployeeEntity> projectMembers;

    public Long getId() {
        return id;
    }

    public ProjectViewModel setId(Long id) {
        this.id = id;
        return this;
    }

    public String getProjectNumber() {
        return projectNumber;
    }

    public ProjectViewModel setProjectNumber(String projectNumber) {
        this.projectNumber = projectNumber;
        return this;
    }

    public String getName() {
        return name;
    }

    public ProjectViewModel setName(String name) {
        this.name = name;
        return this;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public ProjectViewModel setStartDate(LocalDate startDate) {
        this.startDate = startDate;
        return this;
    }

    public String getDuration() {
        return duration;
    }

    public ProjectViewModel setDuration(String duration) {
        this.duration = duration;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ProjectViewModel setDescription(String description) {
        this.description = description;
        return this;
    }

    public List<EmployeeEntity> getProjectMembers() {
        return projectMembers;
    }

    public ProjectViewModel setProjectMembers(List<EmployeeEntity> projectMembers) {
        this.projectMembers = projectMembers;
        return this;
    }
}
