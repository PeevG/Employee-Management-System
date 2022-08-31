package com.example.employemanagementsystem.service.impl;

import com.example.employemanagementsystem.model.entity.UserRoleEntity;
import com.example.employemanagementsystem.model.enums.UserRoleEnum;
import com.example.employemanagementsystem.repository.UserRoleRepository;
import com.example.employemanagementsystem.service.UserRoleService;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class UserRoleServiceImpl implements UserRoleService {

    private final UserRoleRepository userRoleRepository;

    public UserRoleServiceImpl(UserRoleRepository userRoleRepository) {
        this.userRoleRepository = userRoleRepository;
    }

    @Override
    public void seedRoles() {
        if (userRoleRepository.count() < 1) {
            UserRoleEntity adminRole = new UserRoleEntity(UserRoleEnum.ADMIN);
            UserRoleEntity hrManagerRole = new UserRoleEntity(UserRoleEnum.HR_MANAGER);
            UserRoleEntity userRole = new UserRoleEntity(UserRoleEnum.USER);
            UserRoleEntity departmentDirector = new UserRoleEntity(UserRoleEnum.DEPARTMENT_DIRECTOR);

            userRoleRepository.saveAll(List.of(adminRole, hrManagerRole, userRole, departmentDirector));
        }
    }
}
