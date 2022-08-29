package com.example.employemanagementsystem.service.impl;

import com.example.employemanagementsystem.model.binding.UserRegisterBindingModel;
import com.example.employemanagementsystem.model.entity.UserEntity;
import com.example.employemanagementsystem.model.entity.UserRoleEntity;
import com.example.employemanagementsystem.model.enums.UserRoleEnum;
import com.example.employemanagementsystem.repository.UserRepository;
import com.example.employemanagementsystem.repository.UserRoleRepository;
import com.example.employemanagementsystem.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final UserRoleRepository userRoleRepository;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder, UserRoleRepository userRoleRepository) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
        this.userRoleRepository = userRoleRepository;
    }

    @Override
    public void registerUser(UserRegisterBindingModel bindingModel) {
        UserRoleEntity userRole = userRoleRepository.findUserRoleEntityByName(UserRoleEnum.USER);

        UserEntity user = modelMapper.map(bindingModel, UserEntity.class);
        user.setPassword(passwordEncoder.encode(user.getPassword()))
                .setRoles(List.of(userRole));

        userRepository.save(user);

    }

    @Override
    public boolean isUserNameFree(String name) {
        return userRepository.findByUserName(name).isEmpty();
    }

    @Override
    public boolean passwordsChecker(UserRegisterBindingModel bindingModel) {
        return bindingModel.getPassword().equals(bindingModel.getConfirmPassword());
    }

}
