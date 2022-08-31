package com.example.employemanagementsystem.service.impl;

import com.example.employemanagementsystem.exception.ObjectNotFoundException;
import com.example.employemanagementsystem.model.binding.UserLoginBindingModel;
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
    public void seedUsers() {
        if (userRepository.count() < 1) {
            UserEntity admin = new UserEntity();
            admin.setUserName("Admin").setFirstName("Simeon").setLastName("Ivanov").setPassword("123456")
                    .setConfirmPassword("123456").setEmail("admin@abv.bg")
                    .setRoles(List.of(userRoleRepository.getReferenceById(1L)));

            UserEntity hrManager = new UserEntity();
            hrManager.setUserName("HrManager").setFirstName("Nataliq").setLastName("Marinova").setPassword("123456")
                    .setConfirmPassword("123456").setEmail("nat@abv.bg")
                    .setRoles(List.of(userRoleRepository.getReferenceById(2L)));

            UserEntity firstUser = new UserEntity();
            firstUser.setUserName("FirstUser").setFirstName("Martin").setLastName("Aleksiev").setPassword("123456")
                    .setConfirmPassword("123456").setEmail("marto@abv.bg")
                    .setRoles(List.of(userRoleRepository.getReferenceById(3L)));

            userRepository.saveAll(List.of(admin, hrManager, firstUser));
        }
    }

    @Override
    public void registerUser(UserRegisterBindingModel bindingModel) {
        UserRoleEntity role = userRoleRepository.findUserRoleEntityByName(UserRoleEnum.USER);

        UserEntity user = modelMapper.map(bindingModel, UserEntity.class);
        user.setPassword(passwordEncoder.encode(user.getPassword()))
                .setRoles(List.of(role));

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

    @Override
    public void loginUser(UserLoginBindingModel userLoginBindingModel) {
        UserLoginBindingModel user = userRepository.findByEmail(userLoginBindingModel.getEmail())
                .orElseThrow(() ->
                        new ObjectNotFoundException("User with email " + userLoginBindingModel.getEmail() + " is not exist."));
        //Todo: Implement method
    }

}
