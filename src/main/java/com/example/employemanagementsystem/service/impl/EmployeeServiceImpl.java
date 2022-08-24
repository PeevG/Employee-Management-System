package com.example.employemanagementsystem.service.impl;

import com.example.employemanagementsystem.model.binding.EmployeeGetAllDto;
import com.example.employemanagementsystem.repository.EmployeeRepository;
import com.example.employemanagementsystem.service.EmployeeService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, ModelMapper modelMapper) {
        this.employeeRepository = employeeRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<EmployeeGetAllDto> showAllEmployees() {
        return employeeRepository
                .findAll()
                .stream()
                .map(employeeEntity -> modelMapper.map(employeeEntity, EmployeeGetAllDto.class))
                .collect(Collectors.toList());
    }
}
