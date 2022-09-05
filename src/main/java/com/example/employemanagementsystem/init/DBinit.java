package com.example.employemanagementsystem.init;

import com.example.employemanagementsystem.service.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DBinit implements CommandLineRunner {

    private final EmployeeService employeeService;
    private final DepartmentService departmentService;
    private final UserRoleService userRoleService;
    private final UserService userService;
    private final ProjectService projectService;

    public DBinit(EmployeeService employeeService, DepartmentService departmentService, UserRoleService userRoleService, UserService userService, ProjectService projectService) {
        this.employeeService = employeeService;
        this.departmentService = departmentService;
        this.userRoleService = userRoleService;
        this.userService = userService;
        this.projectService = projectService;
    }

    @Override
    public void run(String... args) throws Exception {
        departmentService.seedDepartments();
        employeeService.seedEmployees();
        userRoleService.seedRoles();
        userService.seedUsers();
        projectService.seedProjects();
    }
}
