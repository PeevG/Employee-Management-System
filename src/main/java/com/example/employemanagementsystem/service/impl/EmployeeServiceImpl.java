package com.example.employemanagementsystem.service.impl;

import com.example.employemanagementsystem.exception.ObjectNotFoundException;
import com.example.employemanagementsystem.model.binding.EmployeeAddBindingModel;
import com.example.employemanagementsystem.model.binding.EmployeeGetAllBindingModel;
import com.example.employemanagementsystem.model.binding.EmployeeUpdateBindingModel;
import com.example.employemanagementsystem.model.entity.DepartmentEntity;
import com.example.employemanagementsystem.model.entity.EmployeeEntity;
import com.example.employemanagementsystem.model.entity.ProjectEntity;
import com.example.employemanagementsystem.model.view.EmployeeDetailsViewModel;
import com.example.employemanagementsystem.model.view.EmployeesBasicViewModel;
import com.example.employemanagementsystem.repository.DepartmentRepository;
import com.example.employemanagementsystem.repository.EmployeeRepository;
import com.example.employemanagementsystem.repository.ProjectRepository;
import com.example.employemanagementsystem.service.EmployeeService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;
    private final ProjectRepository projectRepository;
    private final ModelMapper modelMapper;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, DepartmentRepository departmentRepository, ProjectRepository projectRepository, ModelMapper modelMapper) {
        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
        this.projectRepository = projectRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public List<EmployeeGetAllBindingModel> showAllEmployees() {
        return employeeRepository
                .findAll()
                .stream()
                .map(this::mapEmployeeEntityToBindingModel)
                .collect(Collectors.toList());
    }

    @Override
    public void addEmployee(EmployeeAddBindingModel employeeAddBindingModel) {

        EmployeeEntity employee = modelMapper.map(employeeAddBindingModel, EmployeeEntity.class);
        DepartmentEntity department = departmentRepository
                .findByName(employeeAddBindingModel.getDepartment()).orElseThrow(()
                        -> new ObjectNotFoundException("Department with name " + employeeAddBindingModel.getDepartment() + " is not found."));
        employee.setDepartment(department)
                .setHireDate(LocalDate.now());
        this.employeeRepository.save(employee);
    }

    @Override
    public EmployeeUpdateBindingModel getEmployeeById(long id) {
        EmployeeEntity employee = employeeRepository.findById(id)
                .orElseThrow(() ->
                        new ObjectNotFoundException("Employee with " + id + " is not found."));
        return modelMapper.map(employee, EmployeeUpdateBindingModel.class);
    }

    @Override
    public EmployeeDetailsViewModel getEmployeeDetailsById(long id) {
        EmployeeEntity employee = employeeRepository.findById(id)
                .orElseThrow(() ->
                        new ObjectNotFoundException("Employee with " + id + " is not found."));

        return modelMapper.map(employee, EmployeeDetailsViewModel.class);
    }


    @Override
    public void updateEmployee(EmployeeUpdateBindingModel employeeUpdateBindingModel) {
       EmployeeEntity empEntity = employeeRepository.findById(employeeUpdateBindingModel.getId())
                .orElseThrow(() -> new ObjectNotFoundException("Employee with id " + employeeUpdateBindingModel.getId()
                + " is not found."));

        EmployeeUpdateBindingModel employeeById = getEmployeeById(employeeUpdateBindingModel.getId());
        EmployeeEntity employee = modelMapper.map(employeeById, EmployeeEntity.class);
        DepartmentEntity department = departmentRepository.findByName(employeeUpdateBindingModel.getDepartment()).get();

        employee.setFirstName(employeeUpdateBindingModel.getFirstName())
                .setLastName(employeeUpdateBindingModel.getLastName())
                .setAge(employeeUpdateBindingModel.getAge())
                .setEmail(employeeUpdateBindingModel.getEmail())
                .setHireDate(empEntity.getHireDate())
                .setDepartment(department)
                .setSalary(employeeUpdateBindingModel.getSalary())
                .setId(employeeUpdateBindingModel.getId());

        employeeRepository.save(employee);
    }

    @Override
    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }


    @Override
    public void seedEmployees() {
        if (employeeRepository.count() < 1) {
            EmployeeEntity ivo = new EmployeeEntity().setFirstName("Ivailo").setLastName("Ivailov")
                    .setAge(21).setEmail("ivo@gmail.com").setDepartment(departmentRepository.getReferenceById(1L))
                    .setSalary(BigDecimal.valueOf(2000)).setHireDate(LocalDate.now());

            EmployeeEntity desi = new EmployeeEntity().setFirstName("Desislava").setLastName("Stoianova")
                    .setAge(27).setEmail("desi@gmail.com").setDepartment(departmentRepository.getReferenceById(2L))
                    .setSalary(BigDecimal.valueOf(2500)).setHireDate(LocalDate.now());

            EmployeeEntity pesho = new EmployeeEntity().setFirstName("Pesho").setLastName("Petrov")
                    .setAge(32).setEmail("petyr@gmail.com").setDepartment(departmentRepository.getReferenceById(3L))
                    .setSalary(BigDecimal.valueOf(4000)).setHireDate(LocalDate.now());

            EmployeeEntity sasho = new EmployeeEntity().setFirstName("Aleksandyr").setLastName("Dimitrov")
                    .setAge(29).setEmail("sashik@gmail.com").setDepartment(departmentRepository.getReferenceById(1L))
                    .setSalary(BigDecimal.valueOf(5000)).setHireDate(LocalDate.now());

            EmployeeEntity gosho = new EmployeeEntity().setFirstName("Georgi").setLastName("Metodiev")
                    .setAge(31).setEmail("joro@gmail.com").setDepartment(departmentRepository.getReferenceById(1L))
                    .setSalary(BigDecimal.valueOf(7200)).setHireDate(LocalDate.now());
            employeeRepository.saveAll(List.of(ivo, desi, pesho, sasho, gosho));
        }
    }

    @Override
    public Page<EmployeeGetAllBindingModel> getEmployeePageable(Integer pageNo, Integer pageSize, String sortProperty) {

        Pageable pageable;
        if(pageSize == null) {
            pageSize = 7;
        }

        if(sortProperty != null){
            pageable = PageRequest.of(pageNo - 1, pageSize, Sort.by(Sort.Direction.ASC, sortProperty));
        }else {
            pageable = PageRequest.of(pageNo - 1, pageSize, Sort.unsorted());
        }

        Page<EmployeeEntity> entityPage = employeeRepository.findAll(pageable);
        return entityPage
                .map(this::mapEmployeeEntityToBindingModel);
    }

    @Override
    public List<EmployeeGetAllBindingModel> getEmployeesWhoNotInTheCurrentProject(long id) {
        ProjectEntity project = projectRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Project not found!"));
        List<EmployeeEntity> projectMembers = project.getProjectMembers();
        List<EmployeeEntity> all = employeeRepository.findAll();
        for (EmployeeEntity member : projectMembers) {
            all.remove(member);
        }
        return all.stream()
                .map(this::mapEmployeeEntityToBindingModel)
                .collect(Collectors.toList());
    }

    @Override
    public void addEmployeeToProject(long projectId, long employeeId) {
        ProjectEntity project = projectRepository.findById(projectId).orElseThrow(() -> new ObjectNotFoundException("Project not found!"));
        List<EmployeeEntity> projectMembers = project.getProjectMembers();
        EmployeeEntity employee = employeeRepository.findById(employeeId).orElseThrow(() -> new ObjectNotFoundException("Employee not found!"));
        projectMembers.add(employee);
    }

    public EmployeeGetAllBindingModel mapEmployeeEntityToBindingModel(EmployeeEntity employee) {
        EmployeeGetAllBindingModel model = modelMapper.map(employee, EmployeeGetAllBindingModel.class);
        model.setDepartment(employee.getDepartment().getName());
        return model;
    }
}
