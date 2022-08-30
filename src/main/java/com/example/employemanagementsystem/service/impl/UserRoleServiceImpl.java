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
        UserRoleEntity userRole = new UserRoleEntity(UserRoleEnum.USER);
        UserRoleEntity salesManagerRole = new UserRoleEntity(UserRoleEnum.SALES_MANAGER);
        UserRoleEntity hrManagerRole = new UserRoleEntity(UserRoleEnum.HR_MANAGER);
        UserRoleEntity ceo = new UserRoleEntity(UserRoleEnum.CEO);
        UserRoleEntity admin = new UserRoleEntity(UserRoleEnum.ADMIN);

        userRoleRepository.saveAll(List.of(userRole, salesManagerRole, hrManagerRole, ceo, admin));
    }
}
