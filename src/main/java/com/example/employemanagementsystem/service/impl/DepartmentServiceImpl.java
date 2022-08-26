package com.example.employemanagementsystem.service.impl;

import com.example.employemanagementsystem.model.binding.DepartmentAddBindingModel;
import com.example.employemanagementsystem.model.view.DepartmentViewModel;
import com.example.employemanagementsystem.repository.DepartmentRepository;
import com.example.employemanagementsystem.service.DepartmentService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
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
                .map(departmentEntity -> modelMapper.map(departmentEntity, DepartmentViewModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public void addDepartment(DepartmentAddBindingModel departmentAddBindingModel) {
        //Todo
    }
}
