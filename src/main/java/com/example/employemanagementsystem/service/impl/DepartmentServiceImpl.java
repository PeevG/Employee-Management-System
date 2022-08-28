package com.example.employemanagementsystem.service.impl;

import com.example.employemanagementsystem.exception.ObjectNotFoundException;
import com.example.employemanagementsystem.model.binding.DepartmentAddBindingModel;
import com.example.employemanagementsystem.model.binding.DepartmentUpdateBindingModel;
import com.example.employemanagementsystem.model.entity.DepartmentEntity;
import com.example.employemanagementsystem.model.view.DepartmentViewModel;
import com.example.employemanagementsystem.model.view.EmployeesPerDepartmentViewModel;
import com.example.employemanagementsystem.repository.DepartmentRepository;
import com.example.employemanagementsystem.service.DepartmentService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final ModelMapper modelMapper;

    public DepartmentServiceImpl(DepartmentRepository departmentRepository, ModelMapper modelMapper) {
        this.departmentRepository = departmentRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<DepartmentViewModel> showAllDepartments() {
        return departmentRepository
                .findAll()
                .stream()
                .map(this::mapDepEntityToDepViewModel)
                .collect(Collectors.toList());
    }

    @Override
    public void addDepartment(DepartmentAddBindingModel departmentAddBindingModel) {
        DepartmentEntity department = modelMapper.map(departmentAddBindingModel, DepartmentEntity.class);
        department.setEmployeeEntities(new ArrayList<>());
        departmentRepository.save(department);
    }

    @Override
    public void updateDepartment(DepartmentUpdateBindingModel departmentUpdateBindingModel) {
        departmentRepository.findById(departmentUpdateBindingModel.getId())
                .orElseThrow(() -> new ObjectNotFoundException("Department with name " + departmentUpdateBindingModel.getName() + " is not found"));
        DepartmentEntity department = modelMapper.map(departmentUpdateBindingModel, DepartmentEntity.class);
        departmentRepository.save(department);
    }

    @Override
    public DepartmentUpdateBindingModel getById(Long id) {
        DepartmentEntity departmentEntity = departmentRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Department with id " + id + " is not found."));
        return modelMapper.map(departmentEntity, DepartmentUpdateBindingModel.class);
    }

    @Override
    public void deleteDepartment(Long id) {
        DepartmentEntity department = departmentRepository.findById(id)
                .orElseThrow(() ->
                        new ObjectNotFoundException("Department with id " + id + " is not found."));
        departmentRepository.delete(department);
    }

    @Override
    public List<EmployeesPerDepartmentViewModel> getEmployeesByDepartment(Long id) {
        DepartmentEntity dep = departmentRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Not found"));

        List<EmployeesPerDepartmentViewModel> viewModels = dep.getEmployeeEntities()
                .stream()
                .map(employee -> modelMapper.map(employee, EmployeesPerDepartmentViewModel.class))
                .collect(Collectors.toList());
        return viewModels;
    }

    @Override
    public boolean findDepartmentByName(String name) {
        Optional<DepartmentEntity> optionalDepartment = departmentRepository.findByName(name);
        return optionalDepartment.isPresent();
    }


    DepartmentViewModel mapDepEntityToDepViewModel(DepartmentEntity department) {
        List<EmployeesPerDepartmentViewModel> employees = department
                .getEmployeeEntities()
                .stream()
                .map(employee -> modelMapper.map(employee, EmployeesPerDepartmentViewModel.class))
                .collect(Collectors.toList());

        DepartmentViewModel model = modelMapper.map(department, DepartmentViewModel.class);

        model.setEmployees(employees);
        return model;
    }
}
