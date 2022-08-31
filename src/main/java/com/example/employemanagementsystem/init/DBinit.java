package com.example.employemanagementsystem.init;

import com.example.employemanagementsystem.service.DepartmentService;
import com.example.employemanagementsystem.service.EmployeeService;
import com.example.employemanagementsystem.service.UserRoleService;
import com.example.employemanagementsystem.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DBinit implements CommandLineRunner {

    private final EmployeeService employeeService;
    private final DepartmentService departmentService;
    private final UserRoleService userRoleService;
    private final UserService userService;

    public DBinit(EmployeeService employeeService, DepartmentService departmentService, UserRoleService userRoleService, UserService userService) {
        this.employeeService = employeeService;
        this.departmentService = departmentService;
        this.userRoleService = userRoleService;
        this.userService = userService;
    }

    @Override
    public void run(String... args) throws Exception {
        departmentService.seedDepartments();
        employeeService.seedEmployees();
        userRoleService.seedRoles();
        userService.seedUsers();
    }
}
