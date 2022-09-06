package com.example.employemanagementsystem.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "projects")
public class ProjectEntity extends BaseEntity{

    @Column(name = "project_number", nullable = false)
    private String projectNumber;

    @Column(nullable = false)
    private String name;

    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @Column(nullable = false)
    private String duration;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;

    @ManyToMany()
    private List<EmployeeEntity> projectMembers = new ArrayList<>();

    public LocalDate getStartDate() {
        return startDate;
    }

    public ProjectEntity setStartDate(LocalDate startDate) {
        this.startDate = startDate;
        return this;
    }

    public String getDuration() {
        return duration;
    }

    public ProjectEntity setDuration(String duration) {
        this.duration = duration;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ProjectEntity setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getProjectNumber() {
        return projectNumber;
    }

    public ProjectEntity setProjectNumber(String projectNumber) {
        this.projectNumber = projectNumber;
        return this;
    }

    public String getName() {
        return name;
    }

    public ProjectEntity setName(String name) {
        this.name = name;
        return this;
    }

    public List<EmployeeEntity> getProjectMembers() {
        return projectMembers;
    }

    public ProjectEntity setProjectMembers(List<EmployeeEntity> projectMembers) {
        this.projectMembers = projectMembers;
        return this;
    }
}
