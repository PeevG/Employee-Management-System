package com.example.employemanagementsystem.model.entity;

import com.example.employemanagementsystem.model.enums.UserRoleEnum;

import javax.persistence.*;


@Entity
@Table(name = "roles")
public class UserRoleEntity extends BaseEntity {

    @Column(unique = true)
    @Enumerated(EnumType.STRING)
    private UserRoleEnum name;

    public UserRoleEntity() {
    }

    public UserRoleEntity(UserRoleEnum name) {
        this.name = name;
    }

    public UserRoleEnum getName() {
        return name;
    }

    public UserRoleEntity setName(UserRoleEnum name) {
        this.name = name;
        return this;
    }
}
