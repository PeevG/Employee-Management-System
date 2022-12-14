package com.example.employemanagementsystem.repository;

import com.example.employemanagementsystem.model.entity.UserRoleEntity;
import com.example.employemanagementsystem.model.enums.UserRoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRoleEntity, Long> {

    UserRoleEntity findUserRoleEntityByName(UserRoleEnum name);
}
