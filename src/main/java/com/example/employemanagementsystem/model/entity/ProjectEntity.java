package com.example.employemanagementsystem.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "projects")
public class ProjectEntity extends BaseEntity{

    @Column(name = "project_number", nullable = false)
    private String projectNumber;

    @Column(nullable = false)
    private String name;

    @ManyToMany(mappedBy = "projects")
    private List<EmployeeEntity> projectMembers;

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
