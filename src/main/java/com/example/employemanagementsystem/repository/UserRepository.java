package com.example.employemanagementsystem.repository;

import com.example.employemanagementsystem.model.binding.UserLoginBindingModel;
import com.example.employemanagementsystem.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByUserName(String username);
    Optional<UserLoginBindingModel> findByEmail(String email);
}
