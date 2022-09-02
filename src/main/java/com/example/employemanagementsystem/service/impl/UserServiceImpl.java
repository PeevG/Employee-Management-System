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
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final UserRoleRepository userRoleRepository;
    private final CustomUserServiceImpl customUserService;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder, UserRoleRepository userRoleRepository, CustomUserServiceImpl customUserService) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
        this.userRoleRepository = userRoleRepository;
        this.customUserService = customUserService;
    }

    @Override
    public void seedUsers() {
        if (userRepository.count() < 1) {
            UserEntity admin = new UserEntity();
            admin.setUserName("Admin").setFirstName("Simeon").setLastName("Ivanov").setPassword(passwordEncoder.encode("123456"))
                    .setEmail("admin@abv.bg")
                    .setRoles(List.of(userRoleRepository.findUserRoleEntityByName(UserRoleEnum.USER),
                            userRoleRepository.findUserRoleEntityByName(UserRoleEnum.HR_MANAGER),
                            userRoleRepository.findUserRoleEntityByName(UserRoleEnum.DEPARTMENT_DIRECTOR),
                            userRoleRepository.findUserRoleEntityByName(UserRoleEnum.ADMIN)));

            UserEntity hrManager = new UserEntity();
            hrManager.setUserName("HrManager").setFirstName("Nataliq").setLastName("Marinova").setPassword(passwordEncoder.encode("123456"))
                    .setEmail("nat@abv.bg")
                    .setRoles(List.of(userRoleRepository.findUserRoleEntityByName(UserRoleEnum.HR_MANAGER),
                            userRoleRepository.findUserRoleEntityByName(UserRoleEnum.DEPARTMENT_DIRECTOR)));

            UserEntity firstUser = new UserEntity();
            firstUser.setUserName("FirstUser").setFirstName("Martin").setLastName("Aleksiev").setPassword(passwordEncoder.encode("123456"))
                    .setEmail("marto@abv.bg")
                    .setRoles(List.of(userRoleRepository.findUserRoleEntityByName(UserRoleEnum.USER)));

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
        UserEntity userEntity = userRepository.findByUserName(userLoginBindingModel.getUsername())
                .orElseThrow(() ->
                        new ObjectNotFoundException("User with username " + userLoginBindingModel.getUsername() + " is not exist."));

        UserDetails principal = customUserService.loadUserByUsername(userEntity.getUserName());

        Authentication authentication =
                new UsernamePasswordAuthenticationToken(principal, userEntity.getPassword(), principal.getAuthorities());

        authentication.getAuthorities();

        SecurityContextHolder.getContext().setAuthentication(authentication);
    }


}
