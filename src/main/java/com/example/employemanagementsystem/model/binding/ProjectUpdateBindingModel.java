package com.example.employemanagementsystem.model.binding;

import javax.validation.constraints.NotBlank;

public class ProjectUpdateBindingModel {

    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    private String duration;

    @NotBlank
    private String description;

    public Long getId() {
        return id;
    }

    public ProjectUpdateBindingModel setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public ProjectUpdateBindingModel setName(String name) {
        this.name = name;
        return this;
    }

    public String getDuration() {
        return duration;
    }

    public ProjectUpdateBindingModel setDuration(String duration) {
        this.duration = duration;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ProjectUpdateBindingModel setDescription(String description) {
        this.description = description;
        return this;
    }
}
